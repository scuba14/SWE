package Model;

import java.util.ArrayList;
import java.util.List;

public class Transformer {
    public Transformer(List<Transponder> transponders, char[] view) {

        for (int i = 0; i < view.length; i++) {
            if (view[0] == 'S') {
                List<Satellit> satellits = new ArrayList<Satellit>();

                transponders.forEach(transponder -> {
                    if(satellits.stream().noneMatch(o -> o.getName().equals(transponder.getSat()))) {
                        List<Transponder> temp = new ArrayList<Transponder>();
                        temp.add(transponder);
                        List<Channel> tempChannelList = new ArrayList<Channel>(transponder.getChannels());
                        satellits.add(new Satellit(temp, transponder.getSat(),tempChannelList));
                    }else{
                        satellits.stream().filter(o -> o.getName().equals(transponder.getSat()))
                                .forEach(
                                        o -> {
                                            o.getTransponders().add(transponder);
                                            (transponder.getChannels().forEach(
                                                   o.getChannels().add(this) ;
                                            );)
                                        }

                                );
                    }
                });
            }
        }
    }
    public boolean containsSat(final List<Satellit> list, final String name){
        return list.stream().anyMatch(o -> o.getName().equals(name));
    }


}

