import java.util.*;

/* To call choose_move, need to make a hashmap of int,int where keys are 0-8 representing grid:
*  0 1 2
*  3 4 5
*  6 7 8
*  Each value in game state map should be 0 (empty), -1 (player 1's token), or 1 (player 2's token)
*  Choose_move returns a hashmap. example usage below: 
*  HashMap<Integer,Integer> move = choose_move(map, 1); //where map is a hashmap as described above
*/

public class PossibleMoves {
   public static Set<HashMap<Integer,Integer>> findMoves(Map<Integer,Integer> gameboard, int player) {//-1 is plyer 1, 1 is player 2, 0 is empty
      //should be a 3x3 and 
      //data structure - 
      // 0 1 2
      // 3 4 5
      // 6 7 8
      Set<HashMap<Integer,Integer>> currset = new HashSet<HashMap<Integer,Integer>>();
      int count = 0;
      for(Integer index: gameboard.keySet()) {
         if(gameboard.get(index) == player) {
            count+=1;
         }
      }
      boolean add = count < 3; // only add if num pieces for player less than 3
      if(add) {
         for(Integer index: gameboard.keySet()) {
            if(gameboard.get(index) == 0) {
               HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
               for(int elem: gameboard.keySet()) { //make a copy
                  map.put(elem, gameboard.get(elem)); 
               }
               map.put(index,player); 
               currset.add(map);
            }
         }    
      }
      else { 
         for(Integer index: gameboard.keySet()) {
            if(gameboard.get(index)==player) {
               int row = index/3;
               int col = index%3;
               if(col!=0 && gameboard.get(col-1+(row*3)) == 0) { //checks the one to the left
                  HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(); //does it make a copy?
                  for(int elem: gameboard.keySet()) { //make a copy
                     map.put(elem, gameboard.get(elem)); 
                  }
                  map.put(index,0); 
                  map.put(col-1+(row*3),player); 
                  currset.add(map);
               }
               if(col!=2 && gameboard.get(col+1+(row*3)) == 0) { //checks the one to the right
                  HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(); //does it make a copy?
                  for(int elem: gameboard.keySet()) { //make a copy
                     map.put(elem, gameboard.get(elem)); 
                  }
                  map.put(index,0); 
                  map.put(col+1+(row*3),player); 
                  currset.add(map);
               }
               if(row!=2 && gameboard.get(col+((row+1)*3)) == 0) { //checks the one to the bottom
                  HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(); //does it make a copy?
                  for(int elem: gameboard.keySet()) { //make a copy
                     map.put(elem, gameboard.get(elem)); 
                  }
                  map.put(index,0); 
                  map.put(col+((row+1)*3),player); 
                  currset.add(map);
               }
               if(row!=0 && gameboard.get(col+((row-1)*3)) == 0) { //checks the one to the top
                  HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(); 
                  for(int elem: gameboard.keySet()) { //make a copy
                     map.put(elem, gameboard.get(elem)); 
                  }
                  map.put(index,0); 
                  map.put(col+((row-1)*3),player); 
                  currset.add(map);
               }
               if(row < 2 && col < 2 && gameboard.get(col+1+((row+1)*3)) == 0) { //right bottom diagonal
                  HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(); 
                  for(int elem: gameboard.keySet()) { //make a copy
                     map.put(elem, gameboard.get(elem)); 
                  }
                  map.put(index,0); 
                  map.put(col+1+((row+1)*3),player); 
                  currset.add(map);
               }
               if(row > 0 && col > 0 && gameboard.get(col-1+((row-1)*3)) == 0) { //left top diagonal
                  HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(); 
                  for(int elem: gameboard.keySet()) { //make a copy
                     map.put(elem, gameboard.get(elem)); 
                  }
                  map.put(index,0); 
                  map.put(col-1+((row-1)*3),player); 
                  currset.add(map);
               }
               if(row > 0 && col < 2 && gameboard.get(col+1+((row-1)*3)) == 0) { //left bottom diagonal
                  HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(); 
                  for(int elem: gameboard.keySet()) { //make a copy
                     map.put(elem, gameboard.get(elem)); 
                  }
                  map.put(index,0); 
                  map.put(col+1+((row-1)*3),player); 
                  currset.add(map);
               }
               if(row < 2 && col > 0 && gameboard.get(col-1+((row+1)*3)) == 0) { //right top diagonal
                  HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(); 
                  for(int elem: gameboard.keySet()) { //make a copy
                     map.put(elem, gameboard.get(elem)); 
                  }
                  map.put(index,0); 
                  map.put(col-1+((row+1)*3),player); 
                  currset.add(map);
               }
            }
         }
      }
      return currset;
   }
   public static int min_step(Map<Integer,Integer> state,int count) { //player 1
      if(count >=5 ||win(state)!=0) {
         return win(state);
      }
      ArrayList<Integer> a1 = new ArrayList<Integer>();
      for(HashMap<Integer,Integer> hm: findMoves(state, -1)) {
         a1.add(max_step(hm, count+1));
      }
      return Collections.min(a1);
   }
   public static int max_step(Map<Integer,Integer> state, int count) { //player 2
      if(count >=5 ||win(state)!=0) {
         return win(state);
      }
      ArrayList<Integer> a1 = new ArrayList<Integer>();
      for(HashMap<Integer,Integer> hm: findMoves(state, 1)) {
         a1.add(min_step(hm, count+1));
      }
      return Collections.max(a1);
   }
   public static HashMap<Integer,Integer> max_move(Map<Integer,Integer> state) { //player 2 - trying to get max score
      Set<HashMap<Integer,Integer>> set = findMoves(state, 1);
      int max_score = -2; //-2 means that there were no possible moves
      HashMap<Integer,Integer> max_map = null;
      for(HashMap<Integer,Integer> hm: set) {
         int score = min_step(hm,0);
         if (score > max_score) {
            max_score = score;
            max_map = hm;
         }
      }
      return max_map;
   }
   public static HashMap<Integer,Integer> min_move(Map<Integer,Integer> state) { //player 1 - trying to get min score
      Set<HashMap<Integer,Integer>> set = findMoves(state, -1);
      int max_score = 2; //2 means that there were no possible moves
      HashMap<Integer,Integer> max_map = null;
      for(HashMap<Integer,Integer> hm: set) {
         int score = max_step(hm,0);
         if (score < max_score) {
            max_score = score;
            max_map = hm;
         }
      }
      return max_map;
   }
   public static int win(Map<Integer,Integer> gameboard) { // 0 - no win/tie. -1 - player 1 win, 1 - player 2 win. 0 loss
      //find the sums of each row, column, and left and right diagonals. highest sum (3) is win for player 2, lowest sum (-3) is win for player 2.
      int[] sum_arr = new int[8];
      int index = 0;
      for(int i = 0; i < 3; i++) { //find sums of rows
         int sumrow = gameboard.get(0+(i*3))+gameboard.get(1+(i*3))+gameboard.get(2+(i*3));
         sum_arr[index] = sumrow;
         index++;
      }
      for(int j = 0; j < 3; j++) { //find sums of columns
         int sumcol = gameboard.get(j+(0*3))+gameboard.get(j+(1*3))+gameboard.get(j+(2*3));
         sum_arr[index] = sumcol;
         index++;
      }
      int sum_ldiag = gameboard.get(0)+gameboard.get(4)+gameboard.get(8); //find right diagonal sum
      sum_arr[index] = sum_ldiag;
      index++;
      
      int sum_rdiag = gameboard.get(2)+gameboard.get(4)+gameboard.get(6); //find left diagonal sum
      sum_arr[index] = sum_rdiag;
      
      for(int m = 0; m < sum_arr.length; m++) {
         if(sum_arr[m] == 3) { //player 2 won
            return 1;
         }
         if(sum_arr[m] == -3) { //player 1 won
            return -1;
         }
      }
      return 0;
         
   }
   public static HashMap<Integer,Integer> choose_move(Map<Integer,Integer> game_board, int player) { //ai chooses a move after looking 10 moves into the future
      if(player == -1) { //player 1
         HashMap<Integer,Integer> hm = min_move(game_board);
         return hm;
      }
      else { //player 2
         HashMap<Integer,Integer> hm = max_move(game_board);
         return hm;
      }
   }
   public static void testPossibleMoves() { //testing possible moves
      Map<Integer,Integer> map = new HashMap<Integer,Integer>(); //map1
      map.put(0,1);
      map.put(1,1);
      map.put(2,0);
      map.put(3,0);
      map.put(4,0);
      map.put(5,0);
      map.put(6,0);
      map.put(7,0);
      map.put(8,0);
      Set<HashMap<Integer,Integer>> set = findMoves(map, 1);
      System.out.println("empty map test");
      for(HashMap<Integer,Integer> elem: set) {
         System.out.print("{");
         for(Integer i: elem.keySet()) {
            System.out.print(i+":"+elem.get(i)+", ");
         }
         System.out.print("}\n");
      }
      map.put(1,1);
      System.out.println("player 1 - one piece added test");
      set = findMoves(map, 1);
      for(HashMap<Integer,Integer> elem: set) {
         System.out.print("{");
         for(Integer i: elem.keySet()) {
            System.out.print(i+":"+elem.get(i)+", ");
         }
         System.out.print("}\n");
      }
      System.out.println("player 1 - three pieces added test");
      map.put(7,1);
      map.put(3,1);
      set = findMoves(map, 1);
      for(HashMap<Integer,Integer> elem: set) {
         System.out.print("{");
         for(Integer i: elem.keySet()) {
            System.out.print(i+":"+elem.get(i)+", ");
         }
         System.out.print("}\n");
      }
      System.out.println("player 1 - full board test");
      map.put(0,1);
      map.put(1,1);
      map.put(2,1);
      map.put(3,1);
      map.put(4,1);
      map.put(5,1);
      map.put(6,1);
      map.put(7,1);
      map.put(8,1);
      set = findMoves(map, 1);
      for(HashMap<Integer,Integer> elem: set) {
         System.out.print("{");
         for(Integer i: elem.keySet()) {
            System.out.print(i+":"+elem.get(i)+", ");
         }
         System.out.print("}\n");
      }

   }
   public static void main(String[] args) {
      //testPossibleMoves();
      Map<Integer,Integer> map = new HashMap<Integer,Integer>(); //map1
      map.put(0,1);
      map.put(1,0);
      map.put(2,0);
      map.put(3,0);
      map.put(4,0);
      map.put(5,0);
      map.put(6,0);
      map.put(7,0);
      map.put(8,1);
      HashMap<Integer,Integer> move = choose_move(map, 1); 
      for(Integer i: move.keySet()) {
            System.out.print(i+":"+move.get(i)+", ");
      }
      
      
   }
}


