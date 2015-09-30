public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();  
        if(s.length()==0||words.length==0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        for(String st: words){
            if(map.containsKey(st))
                map.put(st, map.get(st)+1);
            else
                map.put(st, 1);
        }
        int len = words[0].length();
        for(int i=0; i<words[0].length(); i++){
            HashMap<String, Integer> cur = new HashMap<>();
            int count = 0;//count how many string(in the list) is included in the current substring
            int left = i;
            for(int j=i; j<=s.length()-words[0].length(); j+=words[0].length()){
                String str = s.substring(j, j+words[0].length());
                if(map.containsKey(str)){
                    if(cur.containsKey(str))
                        cur.put(str, map.get(str) +1);
                    else
                        cur.put(str, 1);
                    if(cur.get(str)<=map.get(str))
                        count++;
                    else{//need to move the left side of window to make sure remove the extra string.
                        while(cur.get(str)>map.get(str)){
                            String tmp = s.substring(left, left + len);
                            if(cur.containsKey(tmp)){
                                cur.put(tmp, cur.get(tmp)-1);
                                if(cur.get(tmp) < map.get(tmp))
                                    count--;
                            }
                            left += len;
                        }
                    }
                    if(count == words.length){
                        res.add(left);
                        String tmp = s.substring(left, left+len);
                        if(cur.containsKey(tmp))
                            cur.put(tmp, cur.get(tmp)-1);
                        count--;
                        left += len;
                    }
                }
                else{
                    cur.clear();
                    count = 0;
                    left = j+len;
                }
            }
        }
        return res;
    }
}
