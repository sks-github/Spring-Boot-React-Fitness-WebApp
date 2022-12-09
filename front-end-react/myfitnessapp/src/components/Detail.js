import React from 'react';
import { Typography, Stack, Button } from '@mui/material';

import BodyPartImage from '../assets/icons/body-part.png';
import TargetImage from '../assets/icons/target.png';
import EquipmentImage from '../assets/icons/equipment.png';
import UserService from '../services/UserService';

const Detail = ({exerciseDetail, username}) => {
    const jwt = localStorage.getItem("accessToken");
    
    const {bodyPart, gifUrl, name, target, equipment, id} = exerciseDetail;
    const extraDetail = [
        {
            icon: BodyPartImage,
            name: bodyPart,
        },
        {
            icon: TargetImage,
            name: target,
        },
        {
            icon: EquipmentImage,
            name: equipment,
        },
    ]

    const handleAddToFav = () => {
        const addToFavDto = {
            username: username,
            exerciseId: id,
            exerciseName: name,
        }
        console.log(addToFavDto)
        UserService.addToFav(jwt, addToFavDto)
            .then((res) => {
                if (res.status === 200) {
                    return res.json();
                  } else if (res.status === 404) {
                    throw new Error("Not found");
                  } else {
                  //   setError(true);
                  }
            }).then((data) => {
                // setUser(data);
                // setLoading(false);
                console.log(data);
            }).catch(err => {
                console.log(err);
            })
    }

  return (
    <Stack gap="60px" sx={{flexDirection:{lg:'row'}, p:'20px', alighItems:'center'}}>
        <img src={gifUrl} alt={name} loading="lazy" className="detail-image"/>
        <Stack sx={{gap:{lg:'35px', xs:'20px'}}}>
            <Button variant='outlined' color='error' onClick={handleAddToFav}>Add to Favourites</Button>
            <Typography variant='h3' textTransform="capitalize">
                {name}
            </Typography>
            {extraDetail.map((item, index) => (
                <Stack key={index} direction="row" gap="24px" alignItems="center">
                    <Button sx={{background:'#fff2db', borderRadius:'50%', width:'100px', height:'100px'}}>
                        <img src={item.icon} alt={bodyPart} style={{width:'50px', height:'50px'}}/>
                    </Button>
                    <Typography textTransform="capitalize" variant="h5">
                        {item.name}
                    </Typography>
                </Stack>
            ))}
        </Stack>
    </Stack>
  )
}

export default Detail;