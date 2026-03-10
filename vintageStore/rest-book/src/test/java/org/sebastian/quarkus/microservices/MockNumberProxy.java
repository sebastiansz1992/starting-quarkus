package org.sebastian.quarkus.microservices;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.test.Mock;

@Mock
@RestClient
public class MockNumberProxy implements NumberProxy {

    @Override
    public IsbThirteen generateIsbNumbers() {
        IsbThirteen isbThirteen = new IsbThirteen();
        isbThirteen.isbn13 = "13-1";
        return isbThirteen;
    }
    
}
