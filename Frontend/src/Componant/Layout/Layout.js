import React, { Fragment } from "react";

import Footer from "../Footer";
import Routers from "../../Routers/Routers";

const  Layout = () => {
  return (
    <Fragment>
      <Fragment>
        <div>
          <Routers />
        </div>
        <Footer />
      </Fragment>
    </Fragment>
  );
};

export default Layout;
