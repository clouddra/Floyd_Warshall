import java.util.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:

//-----------------------------------------------------------------
// If you need additional Java API, import them here:




//-----------------------------------------------------------------
public class Joy {
	private static int INF = 1000000000 ;
	private static int N, M;
	private static int[][] AdjMatrix = new int[80][80];
	//-----------------------------------------------------------------
	// If you need global variables/additional functions, use this space

	private static int[][][] dist = new int[64][64][64] ; 

	//-----------------------------------------------------------------
	// Implement your pre-processing code here.
	// If you do not need this function, simply leave it blank
	//-----------------------------------------------------------------
	public static void preprocessing() {

		for (int i=0 ; i<N ; i++)
			for (int j=0 ; j<N ; j++)
				Arrays.fill(dist[i][j], -1) ;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				dist[i][j][0] = AdjMatrix[i][j];	// distance of i->j for k=0 is direct edge of i->j
			dist[i][i][0] = 0;		// 0 distance to yourself
		}
	}

	//-----------------------------------------------------------------
	// Implement your query code here:
	//-----------------------------------------------------------------
	public static int RP(int S, int T, int K) {

		if (dist[S][T][K]!=-1)	// if in table, get value from table
			return dist[S][T][K] ;

		dist[S][T][K] = INF ; // set to INF to prepare for min()
		for (int i=0 ; i<N ; i++)
			dist[S][T][K] = Math.min(dist[S][T][K], RP(S, i, K-1) + dist[i][T][0]) ;
		dist[T][S][K] = dist[S][T][K] ; //i~>j = j~>i since undirected
		return dist[S][T][K]; 
	}

	//-----------------------------------------------------------------
	// main function: for your reference only
	// Do not modify.
	//-----------------------------------------------------------------
	public static void main(String[] args) {

		/*
// Sample graph for this problem
5 6
0 1 1
0 2 9
1 2 5
1 3 10
2 3 1
3 4 2
		 */

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		AdjMatrix = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(AdjMatrix[i], INF);
		while (M-- > 0) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int T = sc.nextInt();
			AdjMatrix[A][B] = AdjMatrix[B][A] = T;
		}

		preprocessing();
		/*
    // Try attempting Subtask 1 first :)
    for (int S = 0; S < N; S++)
        for (int T = 0; T < N; T++)
            System.out.println(RP(S, T, 0));


    // Subtask 2
    for (int S = 0; S < N; S++)
        for (int T = 0; T < N; T++)
            System.out.println(RP(S, T, N - 2));
		 */
		// Subtask 3
		for (int S = 0; S < N; S++) // issue all possible RP(S, T, K) queries
			for (int T = 0; T < N; T++)
				for (int K = 0; K <= N - 2; K++)
					System.out.println(RP(S, T, K));

	} // end main
} // end class
