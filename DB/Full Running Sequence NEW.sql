CREATE TABLE DEPT
(
	DEPTNO CHAR(1) PRIMARY KEY,
	DNAME VARCHAR(25) NOT NULL,
	MANAGER CHAR(4),
	EX1 FLOAT,
	EX2 VARCHAR(25)   
);

INSERT INTO DEPT (DEPTNO, DNAME, MANAGER)VALUES
('F','FINANCE','F001'),
('R','R & D','R001'),
('P','PRODUCTION','P001'),
('S','SALES','S001');

CREATE TABLE EMP
(
	ENUM CHAR(4) PRIMARY KEY,
    BASIC_SAL FLOAT NOT NULL,
    DEPTNO CHAR(1) NOT NULL,
    JOB CHAR(1) DEFAULT 'W' NOT NULL
    CONSTRAINT CH_EMP_JOB CHECK (JOB IN ('M', 'S', 'W')),
    HOLIDAYS TINYINT DEFAULT 0,
    CITY VARCHAR(4),
    HRA FLOAT,
    EMAIL VARCHAR(50),
    TOTSAL FLOAT,
    HIREDATE DATE NOT NULL,
    PHOTO VARCHAR(25),
    EX1 FLOAT,
    EX2 INT,
    EX3 VARCHAR(25),
    CONSTRAINT FK_DEPT_EMP FOREIGN KEY (DEPTNO)
    REFERENCES DEPT (DEPTNO)
);

DELIMITER //
CREATE TRIGGER AI_EMP
AFTER INSERT
ON EMP FOR EACH ROW
BEGIN
	DECLARE COMM FLOAT;
    DECLARE EXP INT;
    DECLARE HRA FLOAT;
	IF (POSITION('S' IN NEW.ENUM)=1) AND NEW.JOB='W' THEN
		SET EXP=DATEDIFF(SYSDATE(), NEW.HIREDATE);
		IF (EXP>10*365) THEN
			SET COMM = 0.05;
		ELSEIF (EXP>5*365) THEN
			SET COMM=0.04;
		ELSE
			SET COMM=0.03;
        END IF;
        INSERT INTO SALESMAN VALUES (NEW.ENUM, COMM, NEW.CITY);
    END IF;
    INSERT INTO EMP_AUDIT VALUES (NULL, NEW.ENUM, USER(), SYSDATE(), CONCAT(ENUM, ' EMPLOYEE ADDED SUCESSFULLY'));
END; //
DELIMITER ;

CREATE TABLE EMP_DETAILS
(
	ENUM CHAR(4) PRIMARY KEY,
    ENAME VARCHAR(25) NOT NULL,
    MOB BIGINT NOT NULL
    CONSTRAINT CH_EMP_MOB CHECK (LENGTH(MOB)=10),
    GEN CHAR(1) NOT NULL
    CONSTRAINT CH_EMP_GEN CHECK (GEN IN('M', 'F', 'T')),
    AADHAR BIGINT NOT NULL
    CONSTRAINT CH_EMP_AADHAR CHECK (LENGTH(AADHAR)=12),
    DOB DATE NOT NULL,
    CITY VARCHAR(4) NOT NULL,
    PINCODE INT NOT NULL
    CONSTRAINT CH_EMP_PINCODE CHECK (LENGTH(PINCODE)=6),
    ADDRESS TEXT,
    EX1 FLOAT,
    EX2 INT,
    EX3 VARCHAR(25),
    UNIQUE (AADHAR),
    UNIQUE (MOB),
    CONSTRAINT FK_EMP_EMP_DETAILS FOREIGN KEY(ENUM)
    REFERENCES EMP (ENUM) ON DELETE CASCADE
);

DELIMITER //
CREATE TRIGGER AI_EMP_DETAILS
AFTER INSERT
ON EMP_DETAILS FOR EACH ROW
BEGIN
    DECLARE H FLOAT;
    DECLARE TOT FLOAT;
    IF (NEW.CITY IN('BOMB','PUNE', 'DELH', 'CHEN')) THEN 
		SET H=0.5;
	ELSE
		SET H=0.4;
    END IF;
    SET TOT= TOTSALCAL(NEW.ENUM, H,YEAR(SYSDATE()), MONTH(SYSDATE()));
    UPDATE EMP SET HRA=H, EMP.CITY=NEW.CITY, EMP.TOTSAL=TOT WHERE EMP.ENUM=NEW.ENUM;
    INSERT INTO EMP_AUDIT VALUES (NULL, NEW.ENUM, USER(), SYSDATE(), CONCAT(ENUM,' HRA=',H,' AND DETAILS ADDED SUCESSFULLY'));
END; //
DELIMITER ;

CREATE TABLE SALESMAN
(
	SNUM CHAR(4) PRIMARY KEY,
    COMM FLOAT,
    LOC VARCHAR(4) NOT NULL UNIQUE,
    CONSTRAINT FK_EMP_SALESMAN FOREIGN KEY (SNUM)
    REFERENCES EMP (ENUM)
);

CREATE TABLE EMP_AUDIT
(
	SR_NO INT PRIMARY KEY AUTO_INCREMENT,
    ENUM CHAR(4),
    USER VARCHAR(25),
    TIME_STAMP DATETIME,
    REMARK VARCHAR(200)
);

CREATE TABLE OLD_SALARY 
(
	ENUM VARCHAR(255) NOT NULL,
    DEPTNO CHAR(1) NOT NULL,
    MONTH INTEGER, 
    YEAR INTEGER,
	BASIC_SAL FLOAT, 	 
	HOLIDAYS FLOAT NOT NULL, 
    HRA FLOAT NOT NULL, 
	COMM FLOAT,
	TOTALSAL FLOAT NOT NULL
);

DELIMITER //
CREATE FUNCTION TOTSALCAL(EMPNO CHAR(4), H FLOAT, YEAR INTEGER, MON INTEGER)
RETURNS FLOAT
DETERMINISTIC
BEGIN
	DECLARE SALARY FLOAT;
    DECLARE SALPDAY FLOAT;
    DECLARE HOLI TINYINT;
    DECLARE TOT FLOAT;
    DECLARE DEP CHAR(1);
    DECLARE C INTEGER DEFAULT 0;
    SELECT BASIC_SAL, HOLIDAYS,DEPTNO INTO SALARY, HOLI,DEP FROM EMP
    WHERE ENUM=EMPNO;
    SET SALPDAY=SALARY/30;
	SET TOT=SALARY-(SALPDAY*HOLI)+(H*SALARY);
    SELECT COUNT(*) INTO C FROM OLD_SALARY WHERE ENUM=EMPNO && MONTH=MON && YEAR=YEAR;
    IF(C>0) THEN
		UPDATE OLD_SALARY 
			SET HOLIDAYS= SALPDAY*HOLI, HRA=H*SALARY, TOTALSAL=TOT
            WHERE ENUM=EMPNO && MONTH=MON && YEAR=YEAR;
    ELSE
		INSERT INTO OLD_SALARY 
			(ENUM, MONTH, YEAR, DEPTNO, BASIC_SAL, HOLIDAYS, HRA, TOTALSAL)VALUES 
			(EMPNO, MON, YEAR, DEP, SALARY, SALPDAY*HOLI, H*SALARY, TOT);
	END IF;
    RETURN TOT;
END; //
DELIMITER ;

INSERT INTO EMP (ENUM, BASIC_SAL, DEPTNO, JOB, CITY, EMAIL, HIREDATE) VALUES
('F001', 34000, 'F', 'M', 'BOMB', 'ABHISHEK@GMAIL.COM', '1988-09-21'),
('F002', 30000, 'F', 'S', 'BOMB', 'HRISHIK@GMAIL.COM', '1991-08-25'),
('F003', 25000, 'F', 'W', 'BOMB', 'MASIRA@GMAIL.COM', '1998-09-21'),
('F004', 22000, 'F', 'W', 'BOMB', 'SANDEEP@GMAIL.COM', '2004-11-1'),
('R001', 34000, 'R', 'M', 'BOMB', 'SHRIRAM@GMAIL.COM', '1999-12-11'),
('R002', 31000, 'R', 'S', 'BOMB', 'VIJAYALAKSHMI@GMAIL.COM', '2006-7-27'),
('R003', 25000, 'R', 'W', 'PUNE', 'PALLAVI@GMAIL.COM', '2010-4-1'),
('R004', 21000, 'R', 'W', 'BOMB', 'SAIRAJ@GMAIL.COM', '2015-05-19'),
('P001', 32000, 'P', 'M', 'BOMB', 'VASNAT@GMAIL.COM', '1992-05-19'),
('P002', 23000, 'P', 'S', 'BOMB', 'BALAJI@GMAIL.COM', '1997-04-18'),
('P003', 20000, 'P', 'W', 'BOMB', 'ROHAN@GMAIL.COM', '2004-03-29'),
('P004', 19000, 'P', 'W', 'BOMB', 'ADITYA@GMAIL.COM', '2010-09-8'),
('P005', 18000, 'P', 'W', 'BOMB', 'SUMEET@GMAIL.COM', '2015-09-21'),
('S001', 34000, 'S', 'M', 'BOMB', 'SOHAM@GMAIL.COM', '1990-09-20'),
('S002', 21000, 'S', 'W', 'BOMB', 'SOHAN@GMAIL.COM', '2010-08-10'),
('S003', 20000, 'S', 'W', 'PUNE', 'RITESH@GMAIL.COM', '2012-1-11'),
('S004', 20000, 'S', 'W', 'NAGP', 'MARTINRAJ@GMAIL.COM', '2016-09-13'),
('S005', 19000, 'S', 'W', 'DELH', 'KALPESH@GMAIL.COM', '2017-02-15'),
('S006', 18500, 'S', 'W', 'CHEN', 'JABIN@GMAIL.COM', '2018-7-23'),
('S007', 17000, 'S', 'W', 'BEED', 'CHAITANYA@GMAIL.COM', '2019-5-2');

INSERT INTO EMP_DETAILS (ENUM, ENAME, MOB, GEN, AADHAR, DOB, CITY, PINCODE, ADDRESS) VALUES
('F001', 'ABHISHEK KUMAR BHAGAUS', 9986995765,'M', 123456788765, '1971-07-23', 'BOMB', 400622, '29/SHWETAMBARI APT, BHATWADI, THANE'),
('F002', 'HRISHIK SUDHIR SHETTY', 9869989658,'M', 113456788765, '1975-01-30', 'BOMB', 400612, '12/SAI SADAN, KISSAN NAGAR, BHATWADI, THANE'),
('F003', 'MASIRA SHAIKH', 9989995765,'F', 552356788765, '1980-12-3', 'BOMB', 401620, 'B-64/KRANTI TOWERS, NARIMAN POINT, MUMBAI'),
('F004', 'SANDEEP RAMPRAKASH SAHU', 8069995765,'M', 623456778769, '1984-07-23', 'BOMB', 400611, NULL),
('R001', 'SHRIRAM RAMADEVI IYER', 8890095765,'M', 671459978769, '1973-08-13', 'BOMB', 400641, 'Ground Floor 56 Nagdevi St Mandvi, Mumbai'),
('R002', 'VIJAYALAKSHMI IYER', 8891695700,'F', 771454978669, '1981-08-11', 'BOMB', 401641, '234  S.v.road Opp. Railway Station Andheri, Mumbai'),
('R003', 'PALLAVI BAKALE', 8199987657,'F', 451453478721, '1987-09-2', 'PUNE', 400600, '49  Wafa Chs Laxmibe Cheda Marg Nalasopara, Pune'),
('R004', 'SAIRAJ VINAYAK HEGISHTE', 9099095765,'M', 311359998759, '1988-01-11', 'BOMB', 400631, 'Mittal Chambers Yogakshema , Mumbai'),
('P001', 'VASNAT NADAR', 9833095765,'M', 119889998759, '1966-01-17', 'BOMB', 403631, 'D-302 Ttc Indl Area Turbhe Naka Turbhe, Mumbai'),
('P002', 'NADAR BALAJI SHAKTIVEL', 9837895765,'M', 341259998009, '1975-01-31', 'BOMB', 400630, 'W-258257256 Midc Phase Ii Shivaji Udyog Nagar Dombivli, Mumbai'),
('P003', 'ROHAN JANARDAN CHAVAN', 8003095700,'M', 711259998899, '1986-09-19', 'BOMB', 400689, 'Jabbar Complex Begumpet, Hyderabad,Mumbai'),
('P004', 'SINGH ADITYA SUGREEV', 9038995787,'M', 321259998567, '1988-07-1', 'BOMB', 401006, '28  Western Highway Sativali Vasai , Mumbai'),
('P005', 'SUMEET SURESH PILLAI', 8102395787,'M', 561259788346, '1994-01-12', 'BOMB', 400026, '104  Sarang Street Near Crawford Market Mandvi'),
('S001', 'SOHAM BALAKRISHNAN K', 8882395788,'M', 781259788721, '1994-01-12', 'BOMB', 402026, '2/3 Shri Gurudutta Krupa Chs R B Mehta Road Patel Chowk Near Rly Station, THANE'),
('S002', 'SOHAN VISHNU GOWDA', 9702394487,'M', 568759783456, '1994-01-12', 'BOMB', 401121, '424  Plot No  Persipolis Prem Sec  Vashi Navi Mumba'),
('S003', 'RITESH MAHESH SHIKNE', 7920211781,'M', 432259788711, '1994-01-12', 'PUNE', 401025, '42 -b-c Mahavir Bldg Navneet Showroom Bhandark Matunga,,PUNE'),
('S004', 'MARTINRAJ JOHNRAJ NADAR', 8202300001,'M', 761259788123, '1994-01-12', 'NAGP', 401111, NULL),
('S005', 'KALPESH JOTIBA PATIL', 8802225722,'M', 566549788541, '1994-01-12', 'DELH', 401012, '40   Khetwadi Main Road Dr Bhajekar Street Behind Harkisandas Hospital Khetwadi, DELHI'),
('S006', 'JABIN JAGADEESAN NADAR', 7602393181,'M', 331259788776, '1994-01-12', 'CHEN', 401113, '156  Opp Remson Co Ganesh Nagar Charkop Kandivli, CHENNAI'),
('S007', 'CHAITANYA ABHAY GHATKAR', 7502395745,'M', 560259788709, '1994-01-12', 'BEED', 401020, NULL);

CREATE TABLE CUST(
	CNUM INT PRIMARY KEY AUTO_INCREMENT, 
	SNUM CHAR(4),
	CNAME VARCHAR(25) NOT NULL,
	CITY VARCHAR(4) NOT NULL,
	MOB BIGINT NOT NULL UNIQUE
    CONSTRAINT CH_CUST_MOB CHECK (LENGTH(MOB)=10),
	RATING TINYINT NOT NULL,
    PHOTO VARCHAR(25),
	EX1 FLOAT,
	EX2 VARCHAR(25),
	UNIQUE (CNAME, CITY),
	CONSTRAINT FK_SALESMAN_CUST_SNUM FOREIGN KEY(SNUM)
	REFERENCES SALESMAN (SNUM),
    CONSTRAINT FK_SALESMAN_CUST_CITY FOREIGN KEY(CITY)
	REFERENCES SALESMAN (LOC)
);

DELIMITER //
CREATE TRIGGER AI_CUST
AFTER INSERT
ON CUST FOR EACH ROW
BEGIN
    INSERT INTO EMP_AUDIT VALUES (NULL, 0, USER(), SYSDATE(), CONCAT(NEW.CNAME, ' CUSTOMER ADDED SUCESSFULLY'));
END; //
DELIMITER ;

DELIMITER //
CREATE TRIGGER AU_CUST
AFTER UPDATE
ON CUST FOR EACH ROW
BEGIN
    IF (OLD.SNUM != NEW.SNUM ) THEN
		INSERT INTO EMP_AUDIT VALUES (NULL, NEW.CNUM, USER(), SYSDATE(), CONCAT('CUSTOMER ALLOCATED SALESMAN ', NEW.SNUM,' SUCESSFULLY'));
	ELSEIF (OLD.CNAME != NEW.CNAME) THEN
		INSERT INTO EMP_AUDIT VALUES (NULL, NEW.CNUM, USER(), SYSDATE(), CONCAT('CUSTOMER NAME CHANGED TO ', NEW.CNAME,' FROM ', OLD.CNAME, 'SUCESSFULLY'));
	ELSEIF (OLD.CITY != NEW.CITY) THEN
		INSERT INTO EMP_AUDIT VALUES (NULL, NEW.CNUM, USER(), SYSDATE(), CONCAT('CUSTOMER CITY CHANGED TO ', NEW.CITY,' FROM ', OLD.CITY, 'SUCESSFULLY'));
	ELSEIF (OLD.RATING != NEW.RATING) THEN
		INSERT INTO EMP_AUDIT VALUES (NULL, NEW.CNUM, USER(), SYSDATE(), CONCAT('CUSTOMER RATING CHANGED TO ', NEW.RATING,' FROM ', OLD.RATING, 'SUCESSFULLY'));
	END IF;
END; //
DELIMITER ;

INSERT INTO CUST(CNAME, CITY, MOB, RATING) VALUES
('AKSHAY JAGTAP','BOMB',8765456765, 8),
('DEVI DINESH','DELH',8764565434, 9),
('ANIKET PATTIWAR','PUNE',9857623435, 6),
('DISHI KANADE ','CHEN',7686456901, 4),
('DIVYANI INGLE','NAGP',7689898965, 7),
('ASHOK PATE','BOMB',9867675432, 5),
('ANUJ GAONKAR','BEED',7900056700, 10),
('ARVIND BIRADAR','DELH',8765790321, 3);

DELIMITER //
CREATE PROCEDURE CUST_SALESMAN_ALLOCATION (CNO INT)
BEGIN
	DECLARE TEMP_CITY CHAR(4);
    DECLARE SNO CHAR(4);
    SELECT CITY INTO TEMP_CITY FROM CUST WHERE CNUM=CNO; 
    SELECT SNUM INTO SNO FROM SALESMAN WHERE LOC=TEMP_CITY;
    UPDATE CUST SET SNUM=SNO WHERE CNUM=CNO;
    INSERT INTO EMP_AUDIT VALUES (NULL, SNO, USER(), SYSDATE(), CONCAT('SALESMAN ALLOCATED TO CUSTOMER ', CNO ,' SUCESSFULLY'));
END; //
DELIMITER ;

CALL CUST_SALESMAN_ALLOCATION(1);
CALL CUST_SALESMAN_ALLOCATION(2);
CALL CUST_SALESMAN_ALLOCATION(3);
CALL CUST_SALESMAN_ALLOCATION(4);
CALL CUST_SALESMAN_ALLOCATION(5);
CALL CUST_SALESMAN_ALLOCATION(6);
CALL CUST_SALESMAN_ALLOCATION(7);
CALL CUST_SALESMAN_ALLOCATION(8);

CREATE TABLE ORDERS
(
	ONUM INT PRIMARY KEY,
	CNUM INT,
	SNUM CHAR(4),
	ORDER_UNIT INT NOT NULL,
	AMT FLOAT,
	ORDER_DATE DATE,
	STATUSS VARCHAR(25) DEFAULT 'ORDER_PLACED',
	BATCH_NO VARCHAR(25) DEFAULT 'NOT_ASSIGNED',
    CONSTRAINT FK_CUST_ORDERS FOREIGN KEY (CNUM)
    REFERENCES CUST (CNUM),
    UNIQUE (CNUM, ORDER_DATE)
);

SET @PRESENT=0;
SET @REQUIRED=0;

DELIMITER //
CREATE PROCEDURE CHECK_AVAILABILITY(ODATE DATE)
BEGIN
    IF(@PRESENT-@REQUIRED)<0 THEN
		CALL PRODUCTION_REQUEST(ODATE);
    END IF;
END; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE OLD_ORDER_ENTRY(CNO INT, ORDER_UNIT INT,  ORDER_DATE DATE, STATUSS VARCHAR(25), BATCH_NO VARCHAR(25))
BEGIN
	DECLARE ONUM INT;
	DECLARE AMT FLOAT;
    DECLARE SNO CHAR(4);
    SET @REQUIRED=@REQUIRED+ORDER_UNIT;
	SET ONUM=FLOOR(RAND()*(100000-0+1))+0;  
	SET AMT=500*ORDER_UNIT;
    SELECT SNUM INTO SNO FROM CUST WHERE CNUM=CNO;
	INSERT INTO  ORDERS VALUES(ONUM,CNO,SNO,ORDER_UNIT,AMT,ORDER_DATE,STATUSS, BATCH_NO);
    INSERT INTO INCM_EXP_TALLY VALUES ('I', AMT, ORDER_DATE, CONCAT(ONUM, ' INCOME'));
    CALL CHECK_AVAILABILITY(ORDER_DATE);
END; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE NEW_ORDER_ENTRY(CNO INT, ORDER_UNIT INT,  ORDER_DATE DATE, OUT NEW_ONUM INTEGER)
BEGIN
	DECLARE ONO INT;
    DECLARE C INT DEFAULT 0;
	DECLARE AMT FLOAT;
    DECLARE SNO CHAR(4);
	SET ONO=FLOOR(RAND()*(100000-0+1))+0;
    SET @REQUIRED=@REQUIRED+ORDER_UNIT;
    SELECT COUNT(ONUM) INTO C FROM ORDERS WHERE ONUM=ONO;
    WHILE C=1 DO
		SET ONO=FLOOR(RAND()*(100000-0+1))+0;
		SELECT COUNT(ONUM) INTO C FROM ORDERS WHERE ONUM=ONO;
	END WHILE;
    SET NEW_ONUM=ONO;
	SET AMT=500*ORDER_UNIT;
    SELECT SNUM INTO SNO FROM CUST WHERE CNUM=CNO;
	INSERT INTO ORDERS (ONUM, CNUM, SNUM, ORDER_UNIT, AMT, ORDER_DATE)
    VALUES(ONO,CNO,SNO,ORDER_UNIT,AMT,ORDER_DATE);
    INSERT INTO EMP_AUDIT VALUES (NULL, SNO, USER(), SYSDATE(), CONCAT('ORDER NO: ',ONO, ' PLACED FOR CUST NO: ',CNO, ' BY EMP NO: ', SNO));
    INSERT INTO INCM_EXP_TALLY VALUES ('I', AMT, ORDER_DATE, CONCAT(ONO, ' INCOME'));
    CALL CHECK_AVAILABILITY(ORDER_DATE);
END; //
DELIMITER ;

CREATE TABLE INCM_EXP_TALLY
(
	HEAD CHAR(1)
    CONSTRAINT CH_INCM_EXP_TALLY_HEAD CHECK (HEAD IN ('I', 'E', 'P', 'L')),
    AMT FLOAT,
    ENTRY_DATE DATETIME,
    REMARK VARCHAR(200) 
);

CREATE TABLE PROD
(
	BATCH_NO INT PRIMARY KEY AUTO_INCREMENT,
	PRO_DATE DATE,
    UNITS INT DEFAULT 500
);

SET @PRO_UNIT=500;
SET @RAW_COST=140;
SET @BASIC_PROD_COST=75;

DELIMITER //
CREATE PROCEDURE PRODUCTION_REQUEST(PDATE DATE)
BEGIN
	DECLARE PROD_COST FLOAT;
    SET @PRESENT=@PRESENT+@PRO_UNIT;
    INSERT INTO PROD (PRO_DATE) VALUES (DATE(SYSDATE()));
    SET PROD_COST = @PRO_UNIT*(@BASIC_PROD_COST+@RAW_COST);
    INSERT INTO EMP_AUDIT VALUES (NULL, '0', USER(), SYSDATE(), CONCAT(' PRODUCTION REQUEST PLACED SUCESSFULLY'));
    INSERT INTO INCM_EXP_TALLY VALUES ('E', PROD_COST, PDATE, 'PRODUCTION COST');
END; //
DELIMITER ;

CALL PRODUCTION_REQUEST('2020-09-01');

CALL OLD_ORDER_ENTRY(3,250,'2020-09-01','DELIVERED','1');
CALL OLD_ORDER_ENTRY(1,500,'2020-11-15','DELIVERED','1,2');
CALL OLD_ORDER_ENTRY(3,450,'2020-12-01','DELIVERED','2,3');
CALL OLD_ORDER_ENTRY(7,700,'2021-01-01','DELIVERED','3,4');
CALL OLD_ORDER_ENTRY(8,300,'2021-03-05','DELIVERED','4,5');
CALL OLD_ORDER_ENTRY(2,750,'2021-04-05','DELIVERED','5,6');
CALL OLD_ORDER_ENTRY(1,800,'2021-06-30','DELIVERED','6,7,8');
CALL OLD_ORDER_ENTRY(6,500,'2021-09-15','DELIVERED','8,9');
CALL OLD_ORDER_ENTRY(4,250,'2021-11-15','DELIVERED','9');
CALL OLD_ORDER_ENTRY(2,300,'2021-03-12','DELIVERED','10');
CALL OLD_ORDER_ENTRY(5,500,'2022-05-24','IN_TRANSIT','10,11');
CALL OLD_ORDER_ENTRY(4,750,'2022-06-01','IN_TRANSIT','11,12');
CALL OLD_ORDER_ENTRY(3,900,'2022-06-07','PROCESSING', NULL);
CALL NEW_ORDER_ENTRY(1,600, '2022-06-12', @NEW_ONUM);
CALL NEW_ORDER_ENTRY(6,800,'2022-06-15',@NEW_ONUM);

DELIMITER //
CREATE PROCEDURE ORDER_STATUS_UPDATE(ENO CHAR(4), ONO INT, STAT VARCHAR(25), BATCH VARCHAR(25))
BEGIN
	UPDATE ORDERS SET STATUSS=STAT, BATCH_NO=BATCH WHERE ONUM=ONO;
    IF(LENGTH(STAT) = 0) THEN
		INSERT INTO EMP_AUDIT VALUES (NULL, ENO, USER(), SYSDATE(), CONCAT('ORDER NO: ',ONO, ' STATUS UPDATED TO ', STAT));
	ELSE
		INSERT INTO EMP_AUDIT VALUES (NULL, ENO, USER(), SYSDATE(), CONCAT('ORDER NO: ',ONO, ' NOT FOUND FOR UPDATE'));
	END IF;
END; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SALES_COMMISSION(YEAR INTEGER, MON INTEGER)
BEGIN
	DECLARE COM FLOAT;
    DECLARE ORD_TOT FLOAT;
    DECLARE SNO CHAR(4);
    DECLARE HRA_TEMP FLOAT;
    DECLARE FINAL_COMM FLOAT;
    DECLARE TOT FLOAT;
    DECLARE H INT DEFAULT 0;
    DECLARE CU CURSOR FOR 
    SELECT SALESMAN.SNUM, COMM, HRA, SUM(AMT), TOTSAL FROM ORDERS,EMP, SALESMAN
		WHERE ORDERS.SNUM=SALESMAN.SNUM AND SALESMAN.SNUM=EMP.ENUM
		AND MONTH(ORDER_DATE)=MON AND YEAR(ORDER_DATE)=YEAR
		GROUP BY SALESMAN.SNUM;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET H=1;
    OPEN CU ;
		CU_LOOP:LOOP
			FETCH CU INTO SNO, COM, HRA_TEMP, ORD_TOT, TOT;
            IF H=1 THEN
				LEAVE CU_LOOP;
			END IF;
            UPDATE EMP SET TOTSAL=TOT+(COM*ORD_TOT) WHERE ENUM=SNO;
            UPDATE OLD_SALARY SET COMM=COM*ORD_TOT, TOTALSAL=TOT+(COM*ORD_TOT) WHERE ENUM=SNO;
            INSERT INTO EMP_AUDIT VALUES (NULL,SNO, USER(), SYSDATE(), CONCAT(SNO,' MONTHLY SAL+COMMISSION UPDATED IN TOTAL SALARY'));
        END LOOP CU_LOOP;
    CLOSE CU;
END; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE CURRENT_COMMISSION(SNO CHAR(4), OUT COMMISSION FLOAT)
BEGIN
	DECLARE COM FLOAT;
    DECLARE ORD_TOT FLOAT;
    DECLARE FINAL_COMM FLOAT;
    DECLARE TOT FLOAT;
    SELECT COMM, SUM(AMT) INTO COM, ORD_TOT FROM ORDERS,SALESMAN
		WHERE ORDERS.SNUM=SALESMAN.SNUM 
		AND MONTH(ORDER_DATE)=MONTH(SYSDATE()) AND YEAR(ORDER_DATE)=YEAR(SYSDATE())
		GROUP BY SALESMAN.SNUM;
    SET COMMISSION=(COM*ORD_TOT);
END; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE MONTHLY_SAL(YEAR INTEGER, MON INTEGER)
BEGIN
	DECLARE ENO CHAR(4);
    DECLARE TEMP_HRA FLOAT;
    DECLARE FINAL_SAL FLOAT;
    DECLARE H INT DEFAULT 0;
    DECLARE CU CURSOR FOR 
    SELECT ENUM, HRA FROM EMP;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET H=1;
    OPEN CU ;
		CU_LOOP:LOOP
			FETCH CU INTO ENO, TEMP_HRA;
            IF H=1 THEN
				LEAVE CU_LOOP;
			END IF;
            SET FINAL_SAL=TOTSALCAL(ENO, TEMP_HRA,YEAR, MON);
            UPDATE EMP SET TOTSAL=FINAL_SAL WHERE ENUM=ENO;
            INSERT INTO EMP_AUDIT VALUES (NULL, ENO, USER(), SYSDATE(), CONCAT(ENO,' MONTHLY SAL UPDATED IN TOTAL SALARY'));
        END LOOP CU_LOOP;
    CLOSE CU;
    CALL SALES_COMMISSION(YEAR, MON);
END; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE MONTHLY_EXP(MON INTEGER, YEAR INTEGER, ELE FLOAT, RENT FLOAT, MISC FLOAT)
BEGIN
	DECLARE EXP FLOAT;
    DECLARE INC FLOAT;
    DECLARE BALANCE FLOAT;
    DECLARE SALARY FLOAT;
    DECLARE C INTEGER;
    SELECT SUM(TOTSAL) INTO SALARY FROM EMP;
        SELECT SUM(AMT) INTO EXP FROM INCM_EXP_TALLY 
		WHERE HEAD='E' AND MONTH(ENTRY_DATE)=MON AND YEAR(ENTRY_DATE)=YEAR 
        GROUP BY HEAD;
    SELECT SUM(AMT) INTO INC FROM INCM_EXP_TALLY 
		WHERE HEAD='I' AND MONTH(ENTRY_DATE)=MON AND YEAR(ENTRY_DATE)=YEAR
        GROUP BY HEAD;
    SET BALANCE=INC-EXP;
    SELECT COUNT(*) INTO C FROM INCM_EXP_TALLY WHERE REMARK=CONCAT(MON ,'/',YEAR,'--', ' ELECTRICITY BILL');
    IF(C>0) THEN
		DELETE FROM INCM_EXP_TALLY WHERE REMARK=CONCAT(MON ,'/',YEAR,'--', ' ELECTRICITY BILL');
        DELETE FROM INCM_EXP_TALLY WHERE REMARK=CONCAT(MON ,'/',YEAR,'--', ' RENT EXPENDITURE');
		DELETE FROM INCM_EXP_TALLY WHERE REMARK=CONCAT(MON ,'/',YEAR,'--', ' MISCELLANEOUS EXPENDITURE');
		DELETE FROM INCM_EXP_TALLY WHERE REMARK=CONCAT(MON ,'/',YEAR,'--', ' SALARY OF EMPLOYEES');
        DELETE FROM INCM_EXP_TALLY WHERE REMARK=CONCAT('PROFIT FOR ', MON ,'/',YEAR) OR REMARK=CONCAT('LOSS FOR ', MON ,'/',YEAR);
    END IF;
    INSERT INTO INCM_EXP_TALLY VALUES ('E', ELE, SYSDATE(), CONCAT(MON ,'/',YEAR,'--', ' ELECTRICITY BILL'));
	INSERT INTO INCM_EXP_TALLY VALUES ('E', RENT, SYSDATE(), CONCAT(MON ,'/',YEAR,'--', ' RENT EXPENDITURE'));
    INSERT INTO INCM_EXP_TALLY VALUES ('E', MISC, SYSDATE(), CONCAT(MON ,'/',YEAR,'--', ' MISCELLANEOUS EXPENDITURE'));
    INSERT INTO INCM_EXP_TALLY VALUES ('E', SALARY, SYSDATE(), CONCAT(MON ,'/',YEAR,'--', ' SALARY OF EMPLOYEES'));
    IF BALANCE>0 THEN
		INSERT INTO INCM_EXP_TALLY VALUES ('P', BALANCE, SYSDATE(), CONCAT('PROFIT FOR ', MON ,'/',YEAR));
	ELSE
        INSERT INTO INCM_EXP_TALLY VALUES ('L', BALANCE, SYSDATE(), CONCAT('LOSS FOR ', MON ,'/',YEAR));
    END IF;
END; //
DELIMITER ;

CREATE TABLE LOGIN
(
	USERNAME VARCHAR(25) PRIMARY KEY,
	PASSWORD VARCHAR(100) NOT NULL,
	ROLE VARCHAR(15) NOT NULL
    CONSTRAINT CH_LOGIN_ROLE CHECK (ROLE IN ('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_EMPLOYEE')),
	ID VARCHAR(4) NOT NULL
);



CALL MONTHLY_SAL(2022, 6);
CALL MONTHLY_SAL(2020, 4);
CALL MONTHLY_SAL(2021, 3);

CALL MONTHLY_EXP(6, 2022, 50000, 60000, 65000);
CALL MONTHLY_EXP(5, 2022, 50000, 60000, 65000);






