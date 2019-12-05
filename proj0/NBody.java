public class NBody {
    // return radius of the universe in the file
    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    // return bodies
    public static Body[] readBodies(String fileName){
        In in = new In(fileName);
        int num = in.readInt();
        in.readDouble();
        Body[] plants = new Body[num];
        for (int i = 0; i < num; i++){
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String name = in.readString();
            plants[i] = new Body(xp, yp, xv, yv, m, name);
            }
        return plants;
    }

    public static void main(String [] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double uniR = NBody.readRadius(fileName);
        Body[] plants = NBody.readBodies(fileName);

        /** drawing the background */
        //set up the universe
        StdDraw.setScale(- uniR, uniR);
        StdDraw.clear(); // clears the drawing window
        StdDraw.picture(0, 0, "images/starfield.jpg");

        // draw more than one body
        for (Body plant : plants) {
            plant.draw();
        }

        StdDraw.enableDoubleBuffering();

        //Set up a loop to loop until this time variable reaches (and includes) the T from above

        for (double time = 0; time <= T; time = time + dt){
            double[] xForce = new double[plants.length];
            double[] yForce = new double[plants.length];

            // Calculate the net x and y forces for each Body
            for (int i = 0; i < plants.length; i++){
                xForce[i] = plants[i].calcNetForceExertedByX(plants);
                yForce[i] = plants[i].calcNetForceExertedByY(plants);
            }

            //update each body’s position, velocity, and acceleration
            for (int i = 0; i < plants.length; i++){
                plants[i].update(dt, xForce[i], yForce[i]);
            }

            //draw background
            StdDraw.picture(0, 0, "images/starfield.jpg");
            //draw plants
            for (Body plant : plants){
                plant.draw();
            }

            //Show the offscreen buffer
            StdDraw.show();
            //Pause the animation for 10 milliseconds
            StdDraw.pause(10);
        }

    }
}
