import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MyList<K>{
    private LinkedHashMap<K,K> map = new LinkedHashMap<>();
    public void add(K value){
        map.put(value,value);
    }
    public void remove(K value){
        map.remove(value);
    }
    public K get(K value){
        return map.get(value);
    }
    public List<K> asList(){
        List<K> list = new ArrayList<>();
        for(Map.Entry<K, K> entry : map.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }

}
