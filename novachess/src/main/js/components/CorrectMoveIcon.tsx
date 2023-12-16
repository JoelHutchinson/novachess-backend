import React, { useEffect, useState } from 'react';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import { green } from '@mui/material/colors';
import { makeStyles } from '@mui/styles';

interface CorrectMoveIconProps {
  isVisible: boolean;
}

const useStyles = makeStyles({
  checkIcon: {
    color: green[500],
    animation: '$appear 1s ease-in-out',
  },
  '@keyframes appear': {
    '0%': {
      opacity: 0,
      transform: 'scale(0.5)',
    },
    '100%': {
      opacity: 1,
      transform: 'scale(1)',
    },
  },
});

const CorrectMoveIcon: React.FC<CorrectMoveIconProps> = ({ isVisible }) => {
  const [key, setKey] = useState(0);
  const classes = useStyles();

  useEffect(() => {
    if (isVisible) {
      setKey(prevKey => prevKey + 1);
    }
  }, [isVisible]);

  return isVisible ? <CheckCircleIcon key={key} className={classes.checkIcon} /> : null;
};

export default CorrectMoveIcon;