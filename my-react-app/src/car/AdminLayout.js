import {Link, Outlet} from "react-router-dom";
function AdminLayout(){
return (
 <>

 <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Car App</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">


    <ul class="navbar-nav">
        <li class="nav-item">
          <Link class="nav-link" to="/car/list" >Car List</Link> 
        </li>
        <li class="nav-item">
          <Link class="nav-link" to="/car/add" >Car Add</Link> 
        </li>
  </ul>

  </div>
  </div>
</nav>

  <Outlet/>

 </>
);
};
export default AdminLayout;