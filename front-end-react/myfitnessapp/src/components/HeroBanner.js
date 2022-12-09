import React from 'react';
import { Box, Stack, Typography } from '@mui/material';

import BannerImage from '../assets/images/banner.jpg';

const HeroBanner = () => {
  return (
    <Box sx={{
        mt: {lg:'212px', xs:'70px'},
        ml: {sm: '50px'}
    }} position="relative" p="20px">
      <div>
        <Typography color="#FF2625"
        fontWeight="600" fontSize="26px">
            Crave Fitness
        </Typography>
        <Typography fontWeight={700} sx={{ fontSize: {lg: '44px', xs: '40px'}}}>
            No Pain, No Gain
        </Typography>
        {/* <img src={BannerImage} alt="my-fitness-buddy" className='hero-banner-img' /> */}

      </div>
    </Box>
  )
}

export default HeroBanner;