public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	
	public double calcDistance(Planet p){
		double distance;
		double dx = xxPos - p.xxPos;
		double dy = yyPos - p.yyPos;
		distance = dx * dx + dy * dy;
		distance = Math.sqrt(distance);
		return distance;
	}
	
	public double calcForceExertedBy(Planet p){
		double r = calcDistance(p);
		double f = (6.67e-11) * mass * p.mass / (r * r);
		return f;
	}
	
	public double calcForceExertedByX(Planet p){
		double r = calcDistance(p);
		double dx = p.xxPos - xxPos;
		double f = calcForceExertedBy(p);
		double fx = f / r * dx;
		return fx;
	}
	
	public double calcForceExertedByY(Planet p){
		double r = calcDistance(p);
		double dy = p.yyPos - yyPos;
		double f = calcForceExertedBy(p);
		double fy = f / r * dy;
		return fy;
	}
	
	public double calcNetForceExertedByX(Planet[] ps){
		double total_fx = 0;
		for(Planet p:ps){
			if(this.equals(p)==false){
				double fx = calcForceExertedByX(p);
				total_fx += fx;
			}
		}
		return total_fx;
	}
	
	public double calcNetForceExertedByY(Planet[] ps){
		double total_fy = 0;
		for(Planet p:ps){
			if(this.equals(p)==false){
				double fy = calcForceExertedByY(p);
				total_fy += fy;
			}
		}
		return total_fy;
	}
	
	public void update(double dt, double fX, double fY){
		double ax = fX / mass;
		double ay = fY / mass;
		xxVel += ax * dt;
		yyVel += ay * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}
	
	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
		//StdDraw.show();
	}
	
}