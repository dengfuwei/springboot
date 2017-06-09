package com.itopener.tools.log.appender.kafka.logback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.util.CollectionUtils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

import com.itopener.tools.log.appender.kafka.logback.formatter.Formatter;
import com.itopener.tools.log.appender.kafka.logback.formatter.MessageFormatter;
import com.itopener.tools.log.appender.kafka.logback.utils.KafkaLogbackUtil;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public class KafkaLogbackAppender<E> extends UnsynchronizedAppenderBase<E> {
	
	private String loggerNamePrefix;

	/**
	 * Comma-delimited list of host:port pairs to use for establishing the initial
	 * connection to the Kafka cluster.
	 */
	private String bootstrapServers = "localhost:9092";

	/**
	 * Id to pass to the server when making requests; used for server-side logging.
	 */
	private String clientId;

	/**
	 * Additional properties used to configure the client.
	 */
	private Map<String, String> properties = new HashMap<String, String>();

	private Producer<E> producer = new Producer<E>();

	private Ssl<E> ssl = new Ssl<E>();

	private String topic = "kafkaLobackTopic";
	
	private KafkaTemplate<Object, Object> kafkaTemplate;
	
	private Formatter formatter = new MessageFormatter();

	private Map<String, Object> buildCommonProperties() {
		Map<String, Object> properties = new HashMap<String, Object>();
		if (this.bootstrapServers != null) {
			properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG,
					new ArrayList<String>(Collections.singletonList(this.bootstrapServers)));
		}
		if (this.clientId != null) {
			properties.put(CommonClientConfigs.CLIENT_ID_CONFIG, this.clientId);
		}
		if (this.ssl.getKeyPassword() != null) {
			properties.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, this.ssl.getKeyPassword());
		}
		if (this.ssl.getKeystoreLocation() != null) {
			properties.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,
					KafkaLogbackUtil.resourceToPath(new ClassPathResource(this.ssl.getKeystoreLocation())));
		}
		if (this.ssl.getKeystorePassword() != null) {
			properties.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG,
					this.ssl.getKeystorePassword());
		}
		if (this.ssl.getTruststoreLocation() != null) {
			properties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
					KafkaLogbackUtil.resourceToPath(new ClassPathResource(this.ssl.getTruststoreLocation())));
		}
		if (this.ssl.getTruststorePassword() != null) {
			properties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,
					this.ssl.getTruststorePassword());
		}
		if (!CollectionUtils.isEmpty(this.properties)) {
			properties.putAll(this.properties);
		}
		return properties;
	}

	/**
	 * Create an initial map of producer properties from the state of this instance.
	 * <p>
	 * This allows you to add additional properties, if necessary, and override the
	 * default kafkaProducerFactory bean.
	 * @return the producer properties initialized with the customizations defined on this
	 * instance
	 */
	public Map<String, Object> buildProducerProperties() {
		Map<String, Object> properties = buildCommonProperties();
		properties.putAll(this.producer.buildProperties());
		return properties;
	}

    @Override
    public void start() {
    	super.start();
    	ProducerFactory<Object, Object> producerFactory = new DefaultKafkaProducerFactory<Object, Object>(this.buildProducerProperties());
    	KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<Object, Object>(producerFactory);
		kafkaTemplate.setDefaultTopic(this.topic);
		this.kafkaTemplate = kafkaTemplate;
		KafkaLogbackUtil.setKafkaTemplate(kafkaTemplate);
    }
    
    @Override
    public void stop() {
		super.stop();
    }

	@Override
	protected void append(E e) {
		if(e instanceof ILoggingEvent && (loggerNamePrefix == null || ((ILoggingEvent) e).getLoggerName().startsWith(loggerNamePrefix))){
			String message = formatter.format((ILoggingEvent) e);
			kafkaTemplate.sendDefault(message);
		}
	}

	public String getLoggerNamePrefix() {
		return loggerNamePrefix;
	}

	public void setLoggerNamePrefix(String loggerNamePrefix) {
		this.loggerNamePrefix = loggerNamePrefix;
	}

	public String getBootstrapServers() {
		return bootstrapServers;
	}

	public void setBootstrapServers(String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public Producer<E> getProducer() {
		return producer;
	}

	public void setProducer(Producer<E> producer) {
		this.producer = producer;
	}

	public Ssl<E> getSsl() {
		return ssl;
	}

	public void setSsl(Ssl<E> ssl) {
		this.ssl = ssl;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public KafkaTemplate<Object, Object> getKafkaTemplate() {
		return kafkaTemplate;
	}

	public void setKafkaTemplate(KafkaTemplate<Object, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public Formatter getFormatter() {
		return formatter;
	}

	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}

}
