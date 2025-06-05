package net.sepidan.common.dto.messaging;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.sepidan.common.constant.Instances;
import net.sepidan.common.util.DateTimeUtils;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class KafkaHeaderDto implements Serializable {

  private String type;
  private LocalDateTime receivedTimeStamp =
      LocalDateTime.parse(LocalDateTime.now(Clock.system(ZoneOffset.UTC))
          .format(DateTimeUtils.gregorianLongFormat));
  private String requestId;
  private Instances sender;
  private Instances receiver;
  private String version = "1.0";
}
