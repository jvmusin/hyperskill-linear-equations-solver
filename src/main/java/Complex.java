public class Complex {
    public static Complex ZERO = new Complex(0, 0);

    private final double real;
    private final double imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public static Complex parse(String s) {
        int at = s.length() - 1;
        String i = "";
        if (s.endsWith("i")) {
            while (at >= 0) {
                char c = s.charAt(at--);
                i = c + i;
                if (c == '+' || c == '-') break;
            }
        }
        String r = s.substring(0, at + 1);
        double real = r.isEmpty() ? 0 : Double.parseDouble(r);
        double imag;
        if (i.isEmpty()) imag = 0;
        else if (i.equals("i") || i.equals("+i")) imag = 1;
        else if (i.equals("-i")) imag = -1;
        else imag = Double.parseDouble(i.replace("i", ""));
        return new Complex(real, imag);
    }

    public Complex add(Complex x) {
        return new Complex(real + x.real, imag + x.imag);
    }

    public Complex multiply(Complex x) {
        return new Complex(real * x.real - imag * x.imag, real * x.imag + imag * x.real);
    }

    public Complex divide(Complex x) {
        double den = x.real * x.real + x.imag * x.imag;
        double r = (real * x.real + imag * x.imag) / den;
        double i = (x.real * imag - real * x.imag) / den;
        return new Complex(r, i);
    }

    public Complex negative() {
        return new Complex(-real, -imag);
    }

    public boolean isZero() {
        return DoubleUtils.isZero(real) && DoubleUtils.isZero(imag);
    }

    public boolean isNotZero() {
        return !isZero();
    }

    @Override
    public String toString() {
        if (isZero()) return "0";

        String justImag;
        if (DoubleUtils.isZero(imag)) justImag = "";
        else if (DoubleUtils.equals(Math.abs(imag), 1)) justImag = (imag < 0 ? "-" : "") + "i";
        else justImag = imag + "i";

        if (DoubleUtils.isZero(real)) return justImag;
        if (DoubleUtils.isZero(imag)) return real + "";

        if (!justImag.isEmpty() && justImag.charAt(0) != '-') justImag = "+" + justImag;
        return real + justImag;
    }
}
