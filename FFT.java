package P3;

import P2.ExceptionHandled;

/**
 * Created by kelseyedge on 4/1/18.
 */
public class FFT {
    public FFT() throws ExceptionHandled {}

    public Complex[] compute(Complex[] z) throws ExceptionHandled {
        int d = 1;                      //direction must be 1 or -1
        double N = z.length;           //N samples of the signal
        System.out.println(z.length);
        int r = (int) N/2;
        double theta = (-2 * Math.PI * d)/N;
        Complex w,u,j;

        for (int i = 1; i <= N-1;i*=2) {
            ///try complex(Maths.cos..., Math.sin)
            w = new Complex(Math.cos(i*theta),(Math.sin(i*theta) ));

            for (int k = 0; k <= N-1;k+=(2*r)) {
                u = new Complex(1,0);

                for (int m = 0; m <= r-1; m++) {
//                    System.out.println("i: "+i + " k: "+k+" m: " + m+" r: "+r);

                    Complex t = z[k+m].minus(z[k+m+r]);
                    z[k+m]= z[k+m].plus(z[k+m+r]);
                    z[k+m+r]= t.times(u);
                    u = w.times(u);

                }

            }
            r = r/2;

        }

        System.out.println("\n");

        for (int i = 0; i <= N-1; i++) {
            r = i;
            int k = 0;

            for (int m = 1; m <= N-1; m++) {
                k = 2*k + (r%2);
                r = r/2;
                m*=2;
            }

            if(k>i){
                Complex t = z[i];
                z[i] = z[k];
                z[k] = t;
            }
        }
        if (d < 0){
            System.out.println("D: "+d);

            for (int i = 0; i <= N-1; i++) {
                z[i]=z[i].divides(new Complex(N,0));
                System.out.println("i: "+i);

            }
        }


        return z;
    }


    public static void main(String[] args) throws ExceptionHandled {
        FFT fft = new FFT();
    }

}
