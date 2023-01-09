package cn.cocowwy.cocobootopenfeginstarter.autoconfigure.ribbon;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateBasedRule;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/9
 */
public class RouteLocalPriorityRule extends PredicateBasedRule {
    @Override
    public AbstractServerPredicate getPredicate() {
        return null;
    }
}
