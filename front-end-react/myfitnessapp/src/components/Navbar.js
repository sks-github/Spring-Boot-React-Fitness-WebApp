import React, {useContext} from 'react';
import { Link } from 'react-router-dom';
import { Button, Stack, Typography } from '@mui/material';

import Logo from '../assets/images/Logo.png';
import { UserContext } from '../App';

const Navbar = () => {
  const {user, setUser} = useContext(UserContext);
  
  const handleLogout = (e) => {
    e.preventDefault();
    //change isLoggedIn to false and loggedInUser = null
    setUser(null);
    localStorage.setItem("accessToken", null);
    localStorage.setItem("loggedInUser", null);
    console.log('logged out successfully');
    window.location.href = "/";
  }

  return (
    <Stack className='navbar' direction="row" justifyContent="space-between" px={"20px"} sx={{gap: {sm: '122px', xs: '40px'}, mt:{sm:'32px', xs:'20px'}}}>
        <Stack direction="row" gap="40px" fontSize="24px" alignItems="flex-end">
            <Link to={"/"}>
                <img src={Logo} alt="logo" style={{width:'48px', height:'48px', margin:'0 20px'}} />
            </Link>
            <Link to={"/"} style={{textDecoration:'none', color:'#3A1212', borderBottom:'3px solid #FF2625'}}>Home</Link>
            <a href='#exercises' style={{textDecoration: 'none', color:'#3A1212'}}>Exercises</a>
        </Stack>
        <Stack direction="row" gap="30px" fontSize="24px" alignItems="center">
            {user ? <Link to="/me" style={{textDecoration:'none', color:'#3A1212', borderBottom:'3px solid #FF2625'}}>Hello, {user}</Link> : <div style={{display:'hidden'}}></div> }
            {user ? <Button onClick={handleLogout} variant='outlined' color='error'>Logout</Button> : <div style={{display:'hidden'}}></div> }
            {!user ? <Link to={"/login"} style={{textDecoration:'none', color:'#3A1212'}}><Button variant='outlined' color='error'>Login</Button></Link> : <div style={{display:'hidden'}}></div> }
            {!user ? <Link to={"/signup"} style={{textDecoration:'none', color:'#3A1212'}}><Button variant='outlined' color='error'>Signup</Button></Link> : <div style={{display:'hidden'}}></div> }
        </Stack>
    </Stack>
  )
}

export default Navbar;