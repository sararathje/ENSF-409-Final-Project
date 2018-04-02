package FrontEnd;

import java.awt.*;

public class LoginWindowConstraints {
    private GridBagConstraints constraints;

    /**
     * Constructs an object of type LoginWindowConstraints with the specified values for fillType, weightX,
     * weightY, xPos, yPos, and margin.
     * The values of the data fields are supplied by the given parameters
     * @param fillType fill constraint
     * @param weightX weightx constraint
     * @param weightY weighty constraint
     * @param xPos gridx constraint
     * @param yPos gridy constraint
     * @param margin Insets margin
     */
    public LoginWindowConstraints(int fillType, double weightX, double weightY, int xPos, int yPos,
                                  Insets margin)
    {
        constraints = new GridBagConstraints();

        constraints.fill = fillType;
        constraints.weightx = weightX;
        constraints.weighty = weightY;
        constraints.gridx = xPos;
        constraints.gridy = yPos;
        constraints.insets = margin;
    }

    /**
     * Gets the constraints.
     * @return constraints for login window component
     */
    public GridBagConstraints getConstraints() {
        return constraints;
    }
}
