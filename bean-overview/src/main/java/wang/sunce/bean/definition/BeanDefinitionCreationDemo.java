package wang.sunce.bean.definition;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import wang.sunce.ioc.domain.User;

public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        //1.通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "sunce")
                .addPropertyValue("age", 18);

        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        //2.通过AbstractBeanDefinition构建

        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("name", "sunce").add("age", 19);
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);

    }
}
