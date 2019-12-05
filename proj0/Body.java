public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    // calculate the distance between two bodies
    public double calcDistance(Body b){
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double d = Math.hypot( dx, dy);
        return d;
    }

    // calculate the force exerted by two bodies
    public double calcForceExertedBy(Body b){
        double G = 6.67e-11;
        double r = calcDistance(b);
        double f = G * this.mass * b.mass / (r * r);
        return f;
    }

    // calculate the force exerted by two bodies in X and Y respectively
    public double calcForceExertedByX(Body b){
        double r = calcDistance(b);
        double dx = b.xxPos - this.xxPos;
        double f = calcForceExertedBy(b);
        double fx = dx * f / r;
        return fx;
    }
    public double calcForceExertedByY(Body b){
        double r = calcDistance(b);
        double dy = b.yyPos - this.yyPos;
        double f = calcForceExertedBy(b);
        double fy = dy * f / r;
        return fy;
    }

    //calculate net force in X and Y respectively
    public double calcNetForceExertedByX(Body[] bs){
        double netx = 0;
        for (Body b : bs){
            if (!this.equals(b)){
                netx = netx + calcForceExertedByX(b);
            }
        }
        return netx;
    }
    public double calcNetForceExertedByY(Body[] bs){
        double nety = 0;
        for (Body b : bs){
            if (!this.equals(b)){
                nety = nety + calcForceExertedByY(b);
            }
        }
        return nety;
    }

    // update the body position
    public void update(double dt, double fx, double fy){
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    //draw body image at body position
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
