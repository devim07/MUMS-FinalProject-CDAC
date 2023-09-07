import React, { useState } from 'react';
import * as FaIcons from 'react-icons/fa';
import { Link } from 'react-router-dom';
import { SidebarData } from './SidebarData';
import '../Style/NavbarAdmin.css';
import { IconContext } from 'react-icons';

function NavbarAdmin() {
  return (
    <>
      <div className="row">
        <div className="col col-sm-4 col-2">
          <IconContext.Provider value={{ color: '#fff' }}>
            <nav className='nav-menu active'>
              <ul className='nav-menu-items' >
          
                {SidebarData.map((item, index) => {
                  return (
                    <li key={index} className={item.cName}>
                      <Link to={item.path}>
                        {item.icon}
                        <span>{item.title}</span>
                      </Link>
                    </li>
                  );
                })}
              </ul>
            </nav>
          </IconContext.Provider>
        </div>
      </div> 
    </>
  );
}

export default NavbarAdmin;
