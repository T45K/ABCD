package visitor;

public class Statements {
    public void statements() {
        do ;
        while (false);

        while (false) ;

        for (int i = 0; i < 10; i++) ;

        if (true) ;

        int a = 0;
        switch (a) {
            case 0:
                break;
        }

        synchronized (a) {
            a++;
        }

        try {
            a = 0;
        } catch (final NullPointerException e) {

        }

        final List<Object> list = new ArrayList<>();
        for (final Object obj : list) {

        }
    }
}
