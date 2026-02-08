package org.berlin.csmath;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/**
 * Assisted with chatgpt - gpt version 5 - Feb 8, 2026
 */
public class CSSetOneApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== CS Cheat Sheet ===");
            System.out.println("1. Heap Sort");
            System.out.println("2. Quick Sort");
            System.out.println("3. Hash Table Example");
            System.out.println("4. Binary Search Tree Example");
            System.out.println("5. Dynamic Programming (Fibonacci)");
            System.out.println("6. Greedy Algorithm Example");
            System.out.println("7. DFS Graph Example");
            System.out.println("8. Dijkstra Example");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> heapSortExample();
                case 2 -> quickSortExample();
                case 3 -> hashTableExample();
                case 4 -> bstExample();
                case 5 -> dpFibonacciExample();
                case 6 -> greedyExample();
                case 7 -> dfsGraphExample();
                case 8 -> dijkstraExample();
                case 9 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    // --- 1. Heap Sort ---
    static void heapSortExample() {
        int[] arr = {4, 10, 3, 5, 1};
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int n : arr) heap.add(n);
        for (int i = 0; i < arr.length; i++) arr[i] = heap.poll();
        System.out.println("Heap Sort Result: " + Arrays.toString(arr));
    }

    // --- 2. Quick Sort ---
    static void quickSortExample() {
        int[] arr = {4, 10, 3, 5, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Quick Sort Result: " + Arrays.toString(arr));
    }
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }

    // --- 3. Hash Table ---
    static void hashTableExample() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 23);
        map.put("Bob", 30);
        System.out.println("Hash Table: " + map);
    }

    // --- 4. Binary Search Tree ---
    static class BSTNode {
        int val; BSTNode left, right;
        BSTNode(int v){val=v;}
    }
    static void bstExample() {
        BSTNode root = new BSTNode(10);
        root.left = new BSTNode(5);
        root.right = new BSTNode(15);
        System.out.print("BST InOrder: "); inorder(root); System.out.println();
    }
    static void inorder(BSTNode node){
        if(node==null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    // --- 5. Dynamic Programming Fibonacci ---
    static void dpFibonacciExample() {
        int n = 10;
        int[] dp = new int[n+1];
        dp[0]=0; dp[1]=1;
        for(int i=2;i<=n;i++) dp[i]=dp[i-1]+dp[i-2];
        System.out.println("DP Fibonacci for n=10: " + dp[n]);
    }

    // --- 6. Greedy Algorithm Example ---
    static void greedyExample() {
        int[] coins = {25,10,5,1};
        int amount = 63;
        System.out.print("Coins used: ");
        for(int coin: coins){
            while(amount >= coin){
                amount -= coin;
                System.out.print(coin + " ");
            }
        }
        System.out.println();
    }

    // --- 7. DFS Graph Example ---
    static void dfsGraphExample() {
        Map<Integer, List<Integer>> graph = Map.of(
                1,List.of(2,3),
                2,List.of(4),
                3,List.of(4),
                4,List.of()
        );
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS Traversal: ");
        dfs(1, graph, visited);
        System.out.println();
    }
    static void dfs(int node, Map<Integer,List<Integer>> graph, Set<Integer> visited){
        if(visited.contains(node)) return;
        visited.add(node);
        System.out.print(node + " ");
        for(int neighbor : graph.getOrDefault(node, Collections.emptyList()))
            dfs(neighbor, graph, visited);
    }

    // --- 8. Dijkstra Example ---
    static void dijkstraExample() {
        int[][] graph = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        int n = graph.length;
        int src = 0;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for(int i=0;i<n;i++){
            int u=-1, min=Integer.MAX_VALUE;
            for(int j=0;j<n;j++)
                if(!visited[j] && dist[j]<min){ min=dist[j]; u=j;}
            if(u==-1) break;
            visited[u]=true;
            for(int v=0;v<n;v++)
                if(graph[u][v]!=0 && dist[u]+graph[u][v]<dist[v])
                    dist[v]=dist[u]+graph[u][v];
        }

        System.out.println("Dijkstra distances from node 0: " + Arrays.toString(dist));
    }

}