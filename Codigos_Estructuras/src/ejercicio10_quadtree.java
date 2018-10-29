import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SegTree{
    SegTree L, R;
    long sum;
    posAvenue max;
    int from, to, mi;
    
    public SegTree( int from , int to ){
        this.from = from;
        this.to = to;
        this.max = new posAvenue(-1, -1);
        this.sum = 0;
        if( from == to ){
            L = R = null;
            mi = -1;
            return;
        }
        this.mi = (from+to)/2;
        this.L = new SegTree(from , mi);
        this.R = new SegTree(mi+1 , to);
    }
    
    void set( int pos , long num ){
        if( from == to ){
            this.sum = num;
            this.max = new posAvenue(pos, num);
        }else{
            if( pos <= this.mi ) L.set(pos,num);
            else R.set(pos,num);
            sum = L.sum + R.sum;
            max = posAvenue.maxVal(L.max, R.max);            
        }
    }
    
    long maxQuery ( int from , int to ){
        if( this.from == from && this.to == to ) return this.sum;
        if( to <= mi ) return L.maxQuery(from, to);
        else if( mi < from ) return R.maxQuery(from, to);
        return Math.max( L.maxQuery(from, mi) , R.maxQuery(mi+1, to) );
    }
    
    posAvenue maxPos ( int lft , int rgt ){
        if( this.from == lft && this.to == rgt ) return max;
        if( rgt <= mi ) return L.maxPos(lft, rgt);
        else if( mi < lft ) return R.maxPos(lft, rgt);
        posAvenue a = L.maxPos(lft, mi);
        posAvenue b = R.maxPos(mi+1, rgt);
        return posAvenue.maxVal(a, b);
    }
    
    long totalQuery ( int from , int to ){
        if( this.from == from && this.to == to ) return this.sum;
        if( to <= mi ) return L.totalQuery(from, to);
        else if( mi < from ) return R.totalQuery(from, to);
        return L.totalQuery(from, mi) + R.totalQuery(mi+1, to) ;
    }
}

class QuadTree{
    QuadTree A, B, C, D;
    long sum;
    posCity max;
    int fi, ti, fj, tj, mi, mj;

    public QuadTree(int fi, int ti, int fj, int tj) {
        this.fi = fi;
        this.ti = ti;
        this.fj = fj;
        this.tj = tj;
        mi = (fi+ti)/2;
        mj = (fj+tj)/2;
        if( fi == ti && fj == tj ){
            A = null;
            B = null;
            C = null;
            D = null;
            sum = 0;
            max = new posCity(fi, fj, sum);
        }else if( fi == ti ){
            A = new QuadTree(fi, mi, fj, mj);
            B = new QuadTree(fi, mi, mj+1, tj);
            C = null;
            D = null;
            sum = A.sum + B.sum;
            max = posCity.maxVal(A.max, B.max);
        }else if( fj == tj ){
            A = new QuadTree(fi, mi, fj, mj);
            B = null;
            C = new QuadTree(mi+1, ti, fj, mj);            
            D = null;
            sum = A.sum + C.sum;
            max = posCity.maxVal(A.max, C.max);
        }else{
            A = new QuadTree(fi, mi, fj, mj);
            B = new QuadTree(fi, mi, mj+1, tj);
            C = new QuadTree(mi+1, ti, fj, mj);
            D = new QuadTree(mi+1, ti, mj+1, tj);
            sum = A.sum + B.sum + C.sum + D.sum;
            max = posCity.maxVal(A.max, B.max, C.max, D.max);
        }
    }
    
    void set( int pi , int pj , long num ){
        if( pi == fi && pi == ti && pj == fj && pj == tj ){
            this.sum = num;
            this.max = new posCity(pi , pj , num);
        }else if( fi == ti ){
            if( pj <= mj ) A.set(pi,pj,num);
            else B.set(pi,pj,num);
            sum = A.sum + B.sum;
            max = posCity.maxVal(A.max, B.max);
        }else if( fj == tj ){
            if( pi <= mi ) A.set(pi,pj,num);
            else C.set(pi,pj,num);
            sum = A.sum + C.sum;
            max = posCity.maxVal(A.max, C.max);
        }else{
            if( pi <= mi ){
                if( pj <= mj ) A.set(pi,pj,num);
                else B.set(pi,pj,num);
            }else{
                if( pj <= mj ) C.set(pi,pj,num);
                else D.set(pi,pj,num);
            }
            sum = A.sum + B.sum + C.sum + D.sum;
            max = posCity.maxVal(A.max, B.max, C.max, D.max);
        }
    }
    
    long totalQuery( int i1 , int i2 , int j1 , int j2 ){
        if( i1 == fi && i2 == ti && j1 == fj && j2 == tj ){
            return this.sum;
        }else if( fi == ti ){
            if( j2 <= mj ) return A.totalQuery(i1, i2, j1, j2);
            else if( mj < j1 ) return B.totalQuery(i1, i2, j1, j2);
            return A.totalQuery(i1, i2, j1, mj) + B.totalQuery(i1, i2, mj+1, j2);
        }else if( fj == tj ){
            if( i2 <= mi ) return A.totalQuery(i1, i2, j1, j2);
            else if( mi < i1 ) return C.totalQuery(i1, i2, j1, j2);
            return A.totalQuery(i1, mi, j1, j2) + C.totalQuery(mi+1, i2, j1, j2);
        }else{
            if( i2 <= mi ){
                if( j2 <= mj ) return A.totalQuery(i1, i2, j1, j2);
                else if( mj < j1 ) return B.totalQuery(i1, i2, j1, j2);
                else return A.totalQuery(i1, i2, j1, mj) + B.totalQuery(i1, i2, mj+1, j2);
            }else if( mi < i1 ){
                if( j2 <= mj ) return C.totalQuery(i1, i2, j1, j2);
                else if( mj < j1 ) return D.totalQuery(i1, i2, j1, j2);
                else return C.totalQuery(i1, i2, j1, mj) + D.totalQuery(i1, i2, mj+1, j2);
            }else if( j2 <= mj ) return A.totalQuery(i1, mi, j1, j2) + C.totalQuery(mi+1, i2, j1, j2);
            else if( mj < j1 )return B.totalQuery(i1, mi, j1, j2) + D.totalQuery(mi+1, i2, j1, j2);
            return A.totalQuery(i1, mi, j1, mj) + B.totalQuery(i1, mi, mj+1, j2) + C.totalQuery(mi+1, i2, j1, mj) + D.totalQuery(mi+1, i2, mj+1, j2);
        }
    }
    
    posCity maxPos( int i1 , int i2 , int j1 , int j2 ){
        if( i1 == fi && i2 == ti && j1 == fj && j2 == tj ){
            return this.max;
        }else if( fi == ti ){
            if( j2 <= mj ) return A.maxPos(i1, i2, j1, j2);
            else if( mj < j1 ) return B.maxPos(i1, i2, j1, j2);
            return posCity.maxVal( A.maxPos(i1, i2, j1, mj) , B.maxPos(i1, i2, mj+1, j2) );
        }else if( fj == tj ){
            if( i2 <= mi ) return A.maxPos(i1, i2, j1, j2);
            else if( mi < i1 ) return C.maxPos(i1, i2, j1, j2);
            return posCity.maxVal( A.maxPos(i1, mi, j1, j2) , C.maxPos(mi+1, i2, j1, j2) );
        }else{
            if( i2 <= mi ){
                if( j2 <= mj ) return A.maxPos(i1, i2, j1, j2);
                else if( mj < j1 ) return B.maxPos(i1, i2, j1, j2);
                else return posCity.maxVal( A.maxPos(i1, i2, j1, mj) , B.maxPos(i1, i2, mj+1, j2) );
            }else if( mi < i1 ){
                if( j2 <= mj ) return C.maxPos(i1, i2, j1, j2);
                else if( mj < j1 ) return D.maxPos(i1, i2, j1, j2);
                else return posCity.maxVal ( C.maxPos(i1, i2, j1, mj) , D.maxPos(i1, i2, mj+1, j2) );
            }else if( j2 <= mj ) return posCity.maxVal ( A.maxPos(i1, mi, j1, j2) , C.maxPos(mi+1, i2, j1, j2) );
            else if( mj < j1 )return posCity.maxVal ( B.maxPos(i1, mi, j1, j2) , D.maxPos(mi+1, i2, j1, j2) );
            return posCity.maxVal ( A.maxPos(i1, mi, j1, mj) , B.maxPos(i1, mi, mj+1, j2) , C.maxPos(mi+1, i2, j1, mj) , D.maxPos(mi+1, i2, mj+1, j2) );
        }
    }
    
}

class posAvenue{
    int pos;
    long val;

    public posAvenue(int pos, long val) {
        this.pos = pos;
        this.val = val;
    }
    
    @Override
    public String toString(){
        return "" + pos;
    }
    
    static posAvenue maxVal ( posAvenue a , posAvenue b){
        if( a.val > b.val ) return a;
        else if( a.val < b.val ) return b;
        else if( a.pos < b.pos ) return a;
        return b;
    }
}

class posCity{
    int pi, pj;
    long val;

    public posCity(int pi, int pj, long val) {
        this.pi = pi;
        this.pj = pj;
        this.val = val;
    }
    public posCity(int pi , posAvenue av) {
        this.pi = pi;
        this.pj = av.pos;
        this.val = av.val;
    }
    
    @Override
    public String toString(){
        return "" + pi + " " + pj;
    }
    
    static posCity maxVal ( posCity a , posCity b){
        if( a.val > b.val ) return a;
        else if( a.val < b.val ) return b;
        else if( a.pi < b.pi ) return a;
        else if( a.pi > b.pi ) return b;
        else if( a.pj < b.pj ) return a;
        return b;
    }
    
    static posCity maxVal ( posCity a , posCity b , posCity c , posCity d){
        return maxVal( maxVal(a, b) , maxVal(c, d) );
    }
}

public class ejercicio10_quadtree {
    
    static long mod = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String type = br.readLine(), in;
        StringTokenizer token;
        
        if( type.equals("AVENIDA") ){
            SegTree st;
            int n = Integer.parseInt( br.readLine() );
            st = new SegTree(1, n);
            
            long f, a, b;
            in = br.readLine();
            token = new StringTokenizer(in);
            f = Long.parseLong(token.nextToken());
            a = Long.parseLong(token.nextToken());
            b = Long.parseLong(token.nextToken());
            st.set(1, f);
            for( int i = 2 ; i <= n ; i ++ ){
                f = ( a * f ) + b;
                f %= mod;
                st.set(i, f);
            }
            
            while((in = br.readLine())!=null){
                token = new StringTokenizer(in);
                if( token.nextToken().equals("MARCHA") ){
                    int lft = Integer.parseInt( token.nextToken() );
                    int rgt = Integer.parseInt( token.nextToken() );
                    int aux;
                    if( lft > rgt ){
                        aux = lft;
                        lft = rgt;
                        rgt = aux;
                    }
                    System.out.println( st.maxPos(lft , rgt) + " " + st.totalQuery(lft , rgt) );
                }else{
                    int pos = Integer.parseInt( token.nextToken() );
                    long x = Long.parseLong( token.nextToken() );
                    st.set(pos, x);
                }
            }
        }else{            
            QuadTree mt;
            in = br.readLine();
            token = new StringTokenizer(in);
            int n = Integer.parseInt( token.nextToken() );
            int m = Integer.parseInt( token.nextToken() );
            mt = new QuadTree(1, n, 1, m);
            
            long f, a, b, fo;
            in = br.readLine();            
            token = new StringTokenizer(in);
            f = Long.parseLong(token.nextToken());
            a = Long.parseLong(token.nextToken());
            b = Long.parseLong(token.nextToken());
            for( int i = 1 ; i <= n ; i ++ ){
                for( int j = 1 ; j <= m ; j ++ ){
                    mt.set(i, j, f);
                    f = (a * f) + b;
                    f %= mod;
                }
            }
            
            while((in = br.readLine())!=null){
                token = new StringTokenizer(in);
                if( token.nextToken().equals("MARCHA") ){                                        
                    int i1 = Integer.parseInt( token.nextToken() );
                    int j1 = Integer.parseInt( token.nextToken() );
                    int i2 = Integer.parseInt( token.nextToken() );
                    int j2 = Integer.parseInt( token.nextToken() );   
                    int aux;
                    if( i1 > i2 ){
                        aux = i1;
                        i1 = i2;
                        i2 = aux;
                    }
                    if( j1 > j2 ){
                        aux = j1;
                        j1 = j2;
                        j2 = aux;
                    }
                    System.out.println( mt.maxPos( i1, i2, j1, j2 ) + " " + mt.totalQuery( i1, i2, j1, j2 ) );
                }else{
                    int pi = Integer.parseInt( token.nextToken() );
                    int pj = Integer.parseInt( token.nextToken() );
                    long x = Long.parseLong( token.nextToken() );
                    mt.set(pi, pj, x);
                }
            }
        }
    }
    
}