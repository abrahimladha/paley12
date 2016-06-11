import java.util.*;
public class Paley13 {
    public static void main(String[] args){
        System.out.println("test");
        //og graph
        HashMap<String,HashSet<String>> p = new HashMap<>();
            
        for(int i = 0; i < 13; i++)
            p.put(i+"", new HashSet());
        
        for(int i = 0; i < 13; i++){ 
            p.get(i+"").add((i+1)%13 + "");
            p.get(i+"").add((i+3)%13 + "");
            p.get(i+"").add((i+4)%13 + "");
            p.get(i+"").add((i-1+13)%13 + "");
            p.get(i+"").add((i-3+13)%13 + "");
            p.get(i+"").add((i-4+13)%13 + "");
        }
        //System.out.println(p.toString());
        System.out.println(collapse(p,0+"",4+""));
        K5print(collapse(newpaley13(),11+"",10+""));
        /*
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j++){
       //         System.out.println("collapsed" + i + "-" + j);
                K5print(collapse(newpaley13(),i+"",j+""));
            }
        }*/
    }
    public static HashMap<String,HashSet<String>> newpaley13(){
        HashMap<String,HashSet<String>> p = new HashMap<>();
        for(int i = 0; i < 13; i++)
            p.put(i+"", new HashSet());
        for(int i = 0; i < 13; i++){
            p.get(i+"").add((i+1)%13 + "");
            p.get(i+"").add((i+3)%13 + "");
            p.get(i+"").add((i+4)%13 + "");
            p.get(i+"").add((i-1+13)%13 + "");
            p.get(i+"").add((i-3+13)%13 + "");
            p.get(i+"").add((i-4+13)%13 + "");
        }
        return p;
    }
    public static HashMap<String,HashSet<String>> collapse(
                    HashMap<String,HashSet<String>> p, String a, String b){
        if((p.get(a).contains(b))&&(p.get(b).contains(a))){
            System.out.println(a + "-" + b);
            HashMap<String,HashSet<String>> temp = new HashMap<>();
            for(Map.Entry<String, HashSet<String>> entry : p.entrySet()){
                if((!entry.getKey().equals(a)) &&  (!entry.getKey().equals(b)))
                    temp.put(entry.getKey(), entry.getValue());
            }
            for(Map.Entry<String, HashSet<String>> entry : temp.entrySet()){
                if((entry.getValue().contains(a)) || (entry.getValue().contains(b))){
                    entry.getValue().remove(a);
                    entry.getValue().remove(b);
                    entry.getValue().add(a + "-" + b);
                } 
            }
            String s = a + "-" + b;
            temp.put(s, new HashSet<>());
            temp.get(s).addAll(p.get(a));
            temp.get(s).addAll(p.get(b));
            temp.get(s).remove(s);
            temp.get(s).remove(a);
            temp.get(s).remove(b);
            return temp;
        }
        //else System.out.println(a + "-" +b +  " Cant do that. those two vertices do not share an edge");
        return p;
    }
    public static void K5print(
            HashMap<String, HashSet<String>> p){
        //first generate permutation list then see which ones are K5s
        HashSet<String> combinations = new HashSet<>();
        for(Map.Entry<String, HashSet<String>> entry : p.entrySet()){
            combinations.add(entry.getKey());
        }
        //Set<Set<String>> a = powerSet(combinations);
        //System.out.println(a);
        fivegenerator(p);
    }
/*
public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
    Set<Set<T>> sets = new HashSet<Set<T>>();
    if (originalSet.isEmpty()) {
        sets.add(new HashSet<T>());
        return sets;
    }
    List<T> list = new ArrayList<T>(originalSet);
    T head = list.get(0);
    Set<T> rest = new HashSet<T>(list.subList(1, list.size())); 
    for (Set<T> set : powerSet(rest)) {
        Set<T> newSet = new HashSet<T>();
        newSet.add(head);
        newSet.addAll(set);
        sets.add(newSet);
        sets.add(set);
    }
    return sets;
    Set<Set<T>> fives = new HashSet<>();
    for(int i = 0; i < sets.size(); i++){
        if(sets.get(i).size() == 5) fives.add(sets.get(i));
    }
    return fives;
}*/
    public static void fivegenerator(HashMap<String,HashSet<String>> p){
        if(p != null){
        //System.out.println("a");
        int verts = 0;
        ArrayList<String> names = new ArrayList<>();
        for(Map.Entry<String, HashSet<String>> entry : p.entrySet()){
            verts++;
            names.add(entry.getKey());
        }
        HashSet<String> tester = new HashSet<>();
        int counter = 0;
        HashSet<HashSet<String>> possibles = new HashSet<>();
        for(int a = 0; a < verts; a++){
            for(int b = 0; b < verts; b++){
                for(int c = 0; c < verts; c++){
                    for(int d = 0; d < verts; d++){
                        for(int e = 0; e < verts; e++){
                            tester = new HashSet<String>();
                            tester.add(names.get(a));
                            tester.add(names.get(b));
                            tester.add(names.get(c));
                            tester.add(names.get(d));
                            tester.add(names.get(e));
                            if(tester.size() != 5) continue;
                            possibles.add(tester);
                            counter++;
                        }
                    }
                }
            }
        }
        System.out.println(possibles.size());
        //now which of these 792 are real K5s?
        int cout = 0;
        for(HashSet<String> testing : possibles){
            boolean b = true;
            ArrayList<String> testinglist = new ArrayList<>(testing);
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(i != j){
                        HashSet<String> temp = p.get(testinglist.get(i));
                        if(temp.contains(testinglist.get(j))){
                  //          System.out.println(testinglist.get(i));
                  //          System.out.println(testinglist.get(j));
                  //          System.out.println(temp);
                        }
                        else b = false;
                    }
                }
            }
            if(!b) cout++;//System.out.println(testing);
        }
        System.out.println(cout);
    
    }}
}
