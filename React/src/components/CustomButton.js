import React from "react"
import { Button } from "@mui/material"

const CustomButton = ({label, variant = "contained", color = "primary",onClick})=>{
      return(
        <Button
            variant={variant}
            color={color}
            onClick={onClick}
        >
            {label}
        </Button>
      )
}

export default CustomButton;