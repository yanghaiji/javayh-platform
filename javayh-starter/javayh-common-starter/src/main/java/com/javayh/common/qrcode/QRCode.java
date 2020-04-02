package com.javayh.common.qrcode;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 *     二维码
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-01 22:26
 */
@Data
@Builder
public class QRCode implements Serializable {
    /**文本*/
    @NotBlank(message = "content_not_blank")
    private String text;
    /**宽度*/
    private int width;
    /**高度*/
    private int height;
    /**路径*/
    @NotBlank(message = "path_not_blank")
    private String filePath;
}
