package net.sepidan.common.dto.messaging;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class KafkaMessageDto<H extends KafkaHeaderDto, P extends Serializable>
    implements Serializable {
  private H header;
  private P payload;
}
