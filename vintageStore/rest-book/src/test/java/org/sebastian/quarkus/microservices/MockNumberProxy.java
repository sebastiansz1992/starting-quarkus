package org.sebastian.quarkus.microservices;

public class MockNumberProxy implements NumberProxy {

    @Override
    public IsbThirteen generateIsbNumbers() {
        IsbThirteen isbThirteen = new IsbThirteen();
        isbThirteen.isbn13 = "13-mock";
        return isbThirteen;
    }
    
}
