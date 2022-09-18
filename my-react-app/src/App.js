import './App.css';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Welcome from './Welcome';
import AdminLayout from './car/AdminLayout';
import CarList from './car/CarList';
import CarAddForm from './car/CarAddForm';
import CarUpdateForm from './car/CarUpdateForm';
import CarUploadForm from './car/CarUploadForm';

function App() {
  return (
    <div className="App">

      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Welcome/>} ></Route>
          <Route path='/car/' element={<AdminLayout/>} >
            <Route index element={<CarList/>} ></Route>
            <Route path='list' element={<CarList/>} ></Route>
            <Route path='add' element={<CarAddForm/>} ></Route>
            <Route path='update' element={<CarUpdateForm/>} ></Route>
            <Route path='upload' element={<CarUploadForm/>} ></Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}
export default App;
