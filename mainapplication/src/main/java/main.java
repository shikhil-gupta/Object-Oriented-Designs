import java.util.*;

public class main {


    public static void main(String[] args) {
        System.out.println("hello");


        int[][] array = {{1,2}, {2,3}, {3,4}};



        System.out.println(findLongestChain(array));

    }

    public static int findLongestChain(int[][] pairs) {
        int size = pairs.length;
        if(size <=1) {
            return 0;
        }
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[] table = new int[size];
        table[size-1] = 1;
        int maxChain = Integer.MIN_VALUE;
        for(int i=size-2; i>= 0; i--) {
            int a = pairs[i][0];
            int b = pairs[i][1];
            int index = findNextGreaterUsingBST(b, i+1, size-1, pairs);
            if(index != -1) {
                table[i] = table[index] + 1;
            } else {
                table[i] = 1;
            }
            maxChain = Math.max(maxChain, table[i]);
        }
        return maxChain;

    }

    public static int findNextGreaterUsingBST(int value, int start, int end, int[][] pairs) {
        if(start <= end) {
            if(start == end) {
                if(value > pairs[start][0]) {
                    return start;
                }
                return -1;
            }
            if(end == start + 1) {
                if(value > pairs[start][0]) {
                    return start;
                }
                if(value > pairs[end][0]) {
                    return end;
                }
            }

            int mid = (start+end)/2;
            if(value < pairs[mid][0]) {
                return findNextGreaterUsingBST(value,start,mid,pairs);
            } else {
                return findNextGreaterUsingBST(value,mid+1,end,pairs);
            }
        }
        return -1;
    }

    public static boolean isBipartite(int[][] graph) {

        int row = graph.length;
        if(row <= 0) {
            return false;
        }

        Set<Integer> hashSet1 = new HashSet<Integer>();
        Set<Integer> hashSet2 = new HashSet<Integer>();

        for(int i=0; i< row; i++) {

            for(int j=0; j<graph[i].length; j++) {

                if((hashSet1.contains(i) && hashSet1.contains(graph[i][j])) ||
                        (hashSet2.contains(i) && hashSet2.contains(graph[i][j]))) {
                    return false;
                }

                if(hashSet1.contains(i) || hashSet2.contains(i)) {
                    if(hashSet1.contains(i)) {
                        hashSet2.add(graph[i][j]);
                    } else {
                        hashSet1.add(graph[i][j]);
                    }
                } else {
                    hashSet1.add(i);
                    hashSet2.add(graph[i][j]);
                }

            }

        }

        return true;

    }

    public static int findCircleNum(int[][] M) {

        int n = M.length;
        if(n <= 0) {
            return 0;
        }

        int[] parent = new int[n];
        int[] rank = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
            rank[i] =0;
        }
        for(int i=0; i <n; i++){
            for(int j=0; j<n; j++) {
                if(i == j) {
                    continue;
                } else if(M[i][j] == 1) {
                    union(i,j,parent,rank);
                }
            }
        }
        Set<Integer> set = new HashSet<Integer>();
        for(int i=0; i<n; i++) {
            findSet(i,parent);
            set.add(parent[i]);
        }
        return set.size();

    }

    public static boolean isSameSet(int i, int j, int[] parent) {
        int x = findSet(i,parent);
        int y = findSet(j,parent);
        return x==y;
    }

    public static int findSet(int i, int[] parent) {
        if(parent[i]==i) {
            return i;
        }
        return parent[i] = findSet(parent[i], parent);
    }

    public static void union(int i, int j, int[] parent, int[] rank) {

        int x = findSet(i,parent);
        int y = findSet(j,parent);
        if(x != y) {
            if(rank[x] < rank[y]) {
                parent[x] = y;
            } else if(rank[x] > rank[y]) {
                parent[y] = x;
            } else {
                parent[x] = y;
                rank[y] ++;
            }
        }

    }

    public static int maxSumAfterPartitioning(int[] A, int K) {

        int len = A.length;
        if(len <= 0) {
            return 0;
        }
        if(len == 1) {
            return A[0];
        }
        int[] table = new int[A.length];
        table[len-1] = A[len-1];
        for(int i=len-2; i>=0; i--) {

            int arrayMax = Integer.MIN_VALUE;
            int partitionMax = Integer.MIN_VALUE;
            for(int j=i; j<len && j<i+K; j++) {
                partitionMax = Math.max(partitionMax, A[j]);
                arrayMax = Math.max(arrayMax,((j-i+1) * partitionMax) +
                        (j+1 >= len ? 0 : table[j+1]));
            }
            table[i] = arrayMax;
        }
        return table[0];


    }


    public static void generatePowerSet(int start, int end, List<Integer> list) {

        for (Integer k:list) {
                System.out.print(k);
        }
        System.out.println();


        for (int i=start; i<=end; i++) {
            list.add(i);
            generatePowerSet(i+1,end,list);
            list.remove(list.size()-1);

        }

    }






    static int findDuplicate(int[] originalList, int start, int end) {

        if(start <= end) {
            int mid = (start + end)/2;

            if(start == end && hasDuplicate(start, mid, originalList)) {
                return start;
            }

            if(hasDuplicate(start, mid, originalList)) {
                return findDuplicate(originalList,start, mid);
            } else {
                return findDuplicate(originalList,mid+1, end);
            }
        }

        return  -1;
    }

    static boolean hasDuplicate(int start, int end, int[] originalList) {

        int count = 0;

        for(int i : originalList) {
            if(i>=start && i<=end) {
                count++;
            }
        }

        return count <= end-start+1 ? false :true;
    }

}
