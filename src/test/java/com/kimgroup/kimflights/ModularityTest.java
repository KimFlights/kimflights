package com.kimgroup.kimflights;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTest {
    static ApplicationModules modules = ApplicationModules.of(KimflightsApplication.class);

    @Test
    void verifiesModularStructure() {
        modules.verify();
    }
}
