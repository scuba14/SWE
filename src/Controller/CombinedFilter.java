package Controller;
import Controller.Filter;
import Model.Channel;
import Model.Data;
import Model.Satellit;
import Model.Transponder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CombinedFilter implements Filter {
    private Controller.Filter filter;
    private List<Filter> deepfilter;
            @Override
            public Integer filter_data(Satellit sat) {
                Integer ergebnis=filter.filter_data(sat);       // if trans or channel then return true through overload over Filter Interface from concrete Filter (maybe make abstarct class instead of Filter Interface with auto True return)
                //1. It is actually True
                if (ergebnis == 1){
                    return 1;
                }
                //2.  wrong filter --> Transponder Filter or Channel Filter
                else if(ergebnis==0){
                    for (int i = 0; i < sat.getTransonder().size(); i++) {
                        int ret_Trans = filter.filter_data(sat.getTransonder().get(i));
                        if (ret_Trans==-1){
                            // remove if Transponder doesnt match
                            sat.getTransonder().remove(i);
                        }
                        else if (ret_Trans==0){
                            for (int a = 0; i < sat.getTransonder().get(i).getChannels().size(); i++) {
                                int ret_Chan = filter.filter_data(sat.getTransonder().get(i));
                                if (ret_Chan==-1){
                                    // remove if Channel doesnt match
                                    sat.getTransonder().remove(i);
                                }
                        }


                    }
                }
                else if(ergebnis==-1){
                    return 0;           // remove sat on upper level
                }



                //data=this.filter.filter_data(data);
                List<Data> result = new ArrayList<Data>();

                return null;
            }
            @Override
            public Integer filter_data(Transponder transponder) {
                //data=this.filter.filter_data(data);
                List<Data> result = new ArrayList<Data>();
                for (int i = 0; i < this.deepfilter.size(); i++) {
                    //result.set(i, this.deepfilter.get(i).filter_data(data));
                }
                return null;
            }
            @Override
            public Integer filter_data(Channel channel) {
                //data=this.filter.filter_data(data);
                List<Data> result = new ArrayList<Data>();
                for (int i = 0; i < this.deepfilter.size(); i++) {
                    //result.set(i, this.deepfilter.get(i).filter_data(data));
                }
                return null;
            }
}













        /*// Combine data && result structure
        for (int i=0;i<result.size()-1;i++){
            komm ts, sprich j
        }
        return null;

        /*this.deepfilter.stream().forEach(n->n.filter_data());
        Iterator<Filter>iter=this.deepfilter.iterator();
        while(iter.hasNext()){
        Data data_n= iter.next().filter_data();
        Data data_n= iter.next().filter_data();
        nextfilter.filter_data(data);

        */