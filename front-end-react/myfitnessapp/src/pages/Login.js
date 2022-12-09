// import * as React from 'react';
import React, {useContext, useState} from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Login } from '@mui/icons-material';

import AuthService from '../services/AuthService';
import { UserContext } from '../App';

const theme = createTheme();

const SignIn = ({setUser}) =>  {

    // const {user, setUser} = useContext(UserContext);
    const [userCredentials, setUserCredentials] = useState({
        username: "",
        password: "",
      });


      const handleOnChange = (e) => {
        const value = e.target.value;
        setUserCredentials({ ...userCredentials, [e.target.name]: value });
      };

  const handleSignin = (event) => {
    event.preventDefault();
    AuthService.logIn(userCredentials)
      .then((response) => {
        console.log("log in successfull");
        //change userState to loggedIn:true
        setUser(userCredentials.username);
        localStorage.setItem("accessToken", response.headers["authorization"]);
        localStorage.setItem("loggedInUser", userCredentials.username);
        window.location.replace("/");
      })
      .catch((error) => {
        if (error.response) {
          console.log("1: ", error.response);
        } else if (error.request) {
          console.log("2: ", error.request);
        } else {
          console.log(error);
        }
      });
  };

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
          <Box component="form" noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="username"
              value={userCredentials.username}
              onChange={e => handleOnChange(e)}
              label="Username"
              name="username"
              autoComplete="username"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              value={userCredentials.password}
              onChange={e => handleOnChange(e)}
              autoComplete="current-password"
            />
            
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              onClick={handleSignin}
            >
              Sign In
            </Button>
            <Grid container>
              <Grid item xs>
                <Link href="/user/forgottenPassword" variant="body2">
                  Forgot password?
                </Link>
              </Grid>
              <Grid item>
                <Link href="#" variant="body2">
                  {"Don't have an account? Sign Up"}
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}

export default SignIn;