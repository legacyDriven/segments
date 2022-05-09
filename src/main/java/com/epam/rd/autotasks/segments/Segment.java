package com.epam.rd.autotasks.segments;

class Segment {

    private final Point start;

    private final Point end;

    public Segment(Point start, Point end) {
        if (start.equals(end)) throw new IllegalArgumentException("Can non create degenerative segment");
        if (end == null) throw new IllegalArgumentException("Can not create segment from null params");
        this.start = start;
        this.end = end;
    }

    double length() {
        return Math.sqrt(
                Math.pow((this.end.getX() - this.start.getX()), 2)
                        + Math.pow((this.end.getY() - this.start.getY()), 2)
        );
    }

    Point middle() {
        return new Point(
                (this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2
        );
    }



    Point intersection (Segment another){

        double x1= this.start.getX();
        double x2= this.end.getX();
        double x3= another.start.getX();
        double x4= another.end.getX();
        double y1= this.start.getY();
        double y2= this.end.getY();
        double y3= another.start.getY();
        double y4= another.end.getY();

        double denominator = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);

        double bezierT=((x1-x3)*(y3-y4)-(y1-y3)*(x3-x4))/denominator;
        double bezierU=((x1-x3)*(y1-y2)-(y1-y3)*(x1-x2))/denominator;

        if(bezierT < 0.0|| bezierT > 1.0 || bezierU < 0.0 || bezierU > 1.0){
            return null;
        }

        double a=(y1+y2)/(x1+x2);
        double b=y1-a*x1;

        if(y3==a*x3+b&&y4==a*x4) {
            return null;
        }
        double x = x1 + bezierT * (x2-x1);
        double y = y1 + bezierT * (y2-y1);

        return new Point(x,y);
    }

}
