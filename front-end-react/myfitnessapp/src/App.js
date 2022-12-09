import './App.css';
import React, {useEffect, useState} from 'react';
import { Route, Routes } from 'react-router-dom';
import {Box} from '@mui/material';

import Navbar from './components/Navbar';
import Footer from './components/Footer';
import Home from './pages/Home';
import ExerciseDetail from './pages/ExerciseDetail';
import Login from './pages/Login'
import SignUp from './pages/Signup';
import UserProfile from './pages/UserProfile';

export const UserContext = React.createContext();

function App() {
  const [user, setUser] = useState(null);

  useEffect(()=>{
    const u = localStorage.getItem("loggedInUser");
    if(u === "null"){
      setUser(null);
    }else{
      setUser(u);
    }
  }, [user])

  return (
    <UserContext.Provider value={{user, setUser}} >
    <Box width={"400px"} sx={{width:{xl: '1488px'}}} m="auto">
      <Navbar user={user} setUser={setUser} className="navbar"/>
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/me' element={<UserProfile username={user}/>} />
        <Route path='/exercise/:id' element={<ExerciseDetail username={user}/>} />
        <Route path='/login' element={<Login setUser={setUser} />} />
        <Route path='/signup' element={<SignUp setUser={setUser} />} />
      </Routes>
      <Footer />
    </Box>
    </UserContext.Provider>
  );
}

export default App;
