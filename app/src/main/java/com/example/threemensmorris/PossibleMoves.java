import java.util.*;
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
      boolean add = count < 3; // only add if less than 3
      if(add) {
         for(Integer index: gameboard.keySet()) {
            if(gameboard.get(index) == 0) {
               HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(); //does it make a copy?
               for(int elem: gameboard.keySet()) { //make a copy
                  map.put(elem, gameboard.get(elem)); 
               }
               map.put(index,player); 
               currset.add(map);
            }
         }    
      }
      else { //can you move along diagonal ?
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
               if(row!=2 && gameboard.get(col+((row+1)*3)) == 0) {
                  HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(); //does it make a copy?
                  for(int elem: gameboard.keySet()) { //make a copy
                     map.put(elem, gameboard.get(elem)); 
                  }
                  map.put(index,0); 
                  map.put(col+((row+1)*3),player); 
                  currset.add(map);
               }
               if(row!=0 && gameboard.get(col+((row-1)*3)) == 0) {
                  HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(); //does it make a copy?
                  for(int elem: gameboard.keySet()) { //make a copy
                     map.put(elem, gameboard.get(elem)); 
                  }
                  map.put(index,0); 
                  map.put(col+((row-1)*3),player); 
                  currset.add(map);
               }
            }
         }
      }
      return currset;
   }
   public static int min_step(state,count) { //player 1
      if(count >=10 ||win(state)!=0) {
         return win(state);
      }
      ArrayList<Integer> al = new ArrayList<Integer>();
      for(HashMap<Integer,Integer> hm: findMoves(state, -1)) {
         a1.add(max_step(hm));
      }
      return Collections.min(a1);
   }
   public static int max_step(state,count) { //player 2
      if(count >=10 ||win(state)!=0) {
         return win(state);
      }
      ArrayList<Integer> al = new ArrayList<Integer>();
      for(HashMap<Integer,Integer> hm: findMoves(state, -1)) {
         a1.add(min_step(hm));
      }
      return Collections.max(a1);
   }
   public static int win(Map<Integer,Integer> gameboard) { // 0 - no win/tie. -1 - player 1 win, 1 - player 2 win. 0 loss
      //find the sums of each row, column, and left and right diagonals. highest sum (3) is win for player 2, lowest sum (-3) is win for player 2.
      int sum_arr = new int[8];
      int index = 0;
      for(int i = 0; i < 3; i++) { //find sums of rows
         int sumrow = gameboard[0+(i*3)]+gameboard[1+(i*3)]+gameboard[1+(i*3)];
         sum_arr[index] = sumrow;
         index++;
      }
      for(int j = 0; j < 3; j++) { //find sums of columns
         int sumcol = gameboard[j+(0*3)]+gameboard[j+(1*3)]+gameboard[j+(2*3)];
         sum_arr[index] = sumrow;
         index++;
      }
      int sum_ldiag = gameboard[0]+gameboard[4]+gameboard[8]; //find right diagonal sum
      sum_arr[index] = sum_ldiag;
      index++;
      
      int sum_rdiag = gameboard[2]+gameboard[4]+gameboard[6]; //find left diagonal sum
      sum_arr[index] = sum_rdiag;
      
      for(int m = 0; m < sum_arr.length; m++) {
         if(sum_arr[i] == 3) { //player 2 won
            return 1;
         }
         if(sum_arr[i] == -3) { //player 1 won
            return -1;
         }
      }
      return 0;
         
   }
   public static void testPossibleMoves() {
      Map<Integer,Integer> map = new HashMap<Integer,Integer>(); //map1
      map.put(0,0);
      map.put(1,0);
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
   }
}


