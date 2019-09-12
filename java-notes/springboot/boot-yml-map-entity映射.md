
    参考：https://stackoverflow.com/questions/43506319/map-yaml-to-object-hashmap-in-springboot/43513778

    public class PromotionPolicy {
        private int expiryPeriodInDays;
        private boolean reusable;
        private boolean resetExpiry;
    }

---

    @Configuration
    @ConfigurationProperties(prefix = "promotionPolicies")
    @EnableConfigurationProperties
    @Component
        public class PromotionPolicyConfig {
        private Map<String, PromotionPolicy> policies = new HashMap<String, PromotionPolicy>();
    }
---
    promotionPolicies : 
      policies: 
        P001NN:
                expiryPeriodInDays: 16
                reusable: true
                resetExpiry: false
        P001YN:
                expiryPeriodInDays:1
                reusable:true
                resetExpiry:false
        P001NY:
                expiryPeriodInDays:1
                reusable:false
                resetExpiry:true    

---

    wrong:
    
    promotionPolicies : 
        policies : 
            P001NN :
                PromotionPolicy :
                    expiryPeriodInDays: 16
                    reusable: true
                    resetExpiry: false
            P001YN :
                PromotionPolicy :
                    expiryPeriodInDays:1
                    reusable:true
                    resetExpiry:false
            P001NY :
                PromotionPolicy :
                    expiryPeriodInDays:1
                    reusable:false
                    resetExpiry:true    