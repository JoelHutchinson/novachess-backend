import React, { useEffect, useState } from 'react';
import CancelIcon from '@mui/icons-material/Cancel';
import { red } from '@mui/material/colors';
import { makeStyles } from '@mui/styles';

interface IncorrectMoveIconProps {
  isVisible: boolean;
}

const useStyles = makeStyles({
  cancelIcon: {
    color: red[500],
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

const IncorrectMoveIcon: React.FC<IncorrectMoveIconProps> = ({ isVisible }) => {
  const [key, setKey] = useState(0);
  const classes = useStyles();

  useEffect(() => {
    if (isVisible) {
      setKey(prevKey => prevKey + 1);
    }
  }, [isVisible]);

  return isVisible ? <CancelIcon key={key} className={classes.cancelIcon} /> : null;
};

export default IncorrectMoveIcon;
