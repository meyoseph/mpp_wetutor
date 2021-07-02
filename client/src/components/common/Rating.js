import React, { useState } from "react";
import { FaStar } from "react-icons/fa";

const colors = {
  orange: "#FFBA5A",
  grey: "#a9a9a9",
};

function Rating(props) {
  const [currentValue, ] = useState(5 - props.value);
  const [hoverValue, ] = useState(props.value);
  const stars = Array(5).fill(0);

  const styles = {
    container: {
      display: props.type === "none" ? "" : "flex",
      flexDirection: "column",
      alignItems: "center",
    },
    stars: {
      display: "flex",
      flexDirection: "row",
    },
    textarea: {
      border: "1px solid #a9a9a9",
      borderRadius: 5,
      minHeight: 100,
      width: 300,
    },
    button: {
      border: "1px solid #a9a9a9",
      borderRadius: 5,
      width: 300,
    },
  };

  return (
    <div style={styles.container}>
      <div style={styles.stars}>
        {stars.map((_, index) => {
          return (
            <FaStar
              key={index}
              size={18}
              color={
                (hoverValue || currentValue) > index
                  ? colors.orange
                  : colors.grey
              }
            />
          );
        })}
      </div>
    </div>
  );
}



export default Rating;
