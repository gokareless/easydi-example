package org.gokareless.examles.easydi;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import javax.inject.Provider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.Set;

public class GatewayProvider implements Provider<GatewayContainer> {

    @Override
    public GatewayContainer get() {
        try {
            File file = new File(getClass().getResource("gateways.json").toURI());
            Type listType = new TypeToken<Set<Gateway>>() {
            }.getType();
            Set<Gateway> set = new Gson().fromJson(new FileReader(file), listType);
            GatewayContainer container = new GatewayContainer();
            container.setGateways(set);
            return container;
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return new GatewayContainer();
    }
}