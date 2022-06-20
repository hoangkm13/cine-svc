package com.cinema.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank(message = "Tên tài khoản không được để trống !")
    @Length(max = 20, min = 6)
    private String username;

    @NotBlank(message = "Tên khách hàng không được để trống !")
    private String fullName;

    @NotBlank(message = "Giới tính không được để trống !")
    private String gender;

    @NotBlank(message = "Ngày sinh không được để trống !")
    private String birthOfDate;

    @NotBlank(message = "Tên không được để trống")
    private String firstName;

    @NotBlank(message = "Họ không được để trống")
    private String lastName;

    @NotBlank(message = "Số điện thoại không được để trống !")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không hợp lệ")
    private String mobile;

    @NotNull
    @Length(max = 20, min = 6)
    private String password;

    private String avatar;

    private String role;

    @NotBlank(message = "Mail không được để trống !")
    @Email(message = "Sai định dạng email !")
    @Size(max = 256, message = "Mail Tối đa 256 ký tự !")
    private String email;

    private List<ViewDTO> views = new ArrayList<>();

    private List<FavoriteDTO> favorites = new ArrayList<>();
}
