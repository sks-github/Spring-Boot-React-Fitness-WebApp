import React, {useState} from 'react';
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

import AuthService from '../services/AuthService';

const theme = createTheme();

const SignUp = ({setUser}) => {
    const [userData, setUserData] = useState({
      username: "",
      email: "",
      password: "",
      confirmPassword: "",
    });

    const handleOnChange = (e) => {
        const value = e.target.value;
        setUserData({ ...userData, [e.target.name]: value });
      };

    const handleSignup = (event) => {
      event.preventDefault();
      AuthService.signUp(userData)
      .then((response) => {
        setUser(userData.username);
        localStorage.setItem("accessToken", response.headers["authorization"]);
        localStorage.setItem("loggedInUser", response.headers["username"]);
        console.log("signed up successfully", response);
      })
      .catch((error) => {
        if (error.response) {
          console.log("1: ", error.response);
        } else if (error.request) {
          //This occurs when the browser was able to initiate a request but did not receive a valid answer for any reason
        } else {
          console.log("error: ", error);
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
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign up
          </Typography>
          <Box
            component="form"
            noValidate
            sx={{ mt: 3 }}
          >
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  autoComplete="username"
                  name="username"
                  required
                  fullWidth
                  id="username"
                  value={userData.username}
                  onChange={(e) => handleOnChange(e)}
                  label="Username"
                  autoFocus
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  value={userData.email}
                  onChange={(e) => handleOnChange(e)}
                  label="Email Address"
                  name="email"
                  autoComplete="email"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  value={userData.password}
                  onChange={(e) => handleOnChange(e)}
                  autoComplete="new-password"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="confirmPassword"
                  label="Confirm Password"
                  type="password"
                  id="confirmPassword"
                  value={userData.confirmPassword}
                  onChange={(e) => handleOnChange(e)}
                  autoComplete="confirm-password"
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              onClick={handleSignup}
            >
              Sign Up
            </Button>
            <Grid container justifyContent="flex-end">
              <Grid item>
                <Link href="#" variant="body2">
                  Already have an account? Sign in
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}

export default SignUp;