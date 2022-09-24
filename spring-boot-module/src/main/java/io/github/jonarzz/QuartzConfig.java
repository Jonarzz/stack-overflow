package io.github.jonarzz;

import org.springframework.context.annotation.*;
import org.springframework.scheduling.quartz.*;

@Configuration
class QuartzConfig {

    @Bean
    MethodInvokingJobDetailFactoryBean greetingJobDetailFactoryBean() {
        var jobFactory = new MethodInvokingJobDetailFactoryBean();
        jobFactory.setTargetBeanName("greeting");
        jobFactory.setTargetMethod("sayHello");
        return jobFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean simpleTrigger = new SimpleTriggerFactoryBean();
        simpleTrigger.setJobDetail(greetingJobDetailFactoryBean().getObject());
        simpleTrigger.setStartDelay(1_000);
        simpleTrigger.setRepeatInterval(5_000);
        return simpleTrigger;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        var factory = new SchedulerFactoryBean();
        factory.setTriggers(
                simpleTriggerFactoryBean().getObject(),
                cronTriggerFactoryBean().getObject()
        );
        return factory;
    }

    @Bean
    CronTriggerFactoryBean cronTriggerFactoryBean() {
        var cronTrigger = new CronTriggerFactoryBean();
        cronTrigger.setJobDetail(greetingJobDetailFactoryBean().getObject());
        cronTrigger.setCronExpression("* * * * * ? *");
        return cronTrigger;
    }
}
