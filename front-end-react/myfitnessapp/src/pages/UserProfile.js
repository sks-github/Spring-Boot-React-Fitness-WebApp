import React, {useEffect, useState} from 'react';
import { Box, Button, Stack, Typography } from '@mui/material';

import UserService from '../services/UserService';

const UserProfile = ({username}) => {

    const [user, setUser] = useState({});
    const jwt = localStorage.getItem("accessToken");


    function getUserDetails(username, jwt) {
        UserService
          .getUser(username, jwt)
          .then((res) => {
            if (res.status === 200) {
              return res.json();
            } else if (res.status === 404) {
              throw new Error("Not found");
            } else {
            //   setError(true);
            }
          })
          .then((data) => {
            setUser(data);
            // setLoading(false);
            console.log(data);
          })
          .catch((err) => {
            // setError(true);
            console.log(err);
          });
      }

      useEffect(() => {
        getUserDetails(username, jwt);
      }, [username, jwt]);

  return (
    <Box m="5px" p={'50px'}>
        <Stack direction={"column"} gap="10px">
        <em>Username: </em>
            <Typography variant='h4' style={{textDecoration:'none', color:'#3A1212', marginBottom:'20px'}}>{user.username}</Typography>
        <em>Email: </em>
            <Typography variant='h4' style={{textDecoration:'none', color:'#3A1212', marginBottom:'20px'}}>{user.email}</Typography>
        <em>Name: </em>
            <Typography variant='h4' style={{textDecoration:'none', color:'#3A1212', marginBottom:'20px'}}>{user.name}</Typography>
            <Button variant='outlined' color='error'>Edit Profile</Button>
            <Button variant='outlined' color='error'>My Favourite Exercises</Button>
            
        </Stack>
    </Box>
  )
}

export default UserProfile;