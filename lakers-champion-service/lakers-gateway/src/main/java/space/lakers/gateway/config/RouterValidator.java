package space.lakers.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author mini kobe
 * @date 2021/8/22
 * <pre>
 *
 * </pre>
 */
//@Component
public class RouterValidator {

  // public static final List<String> openApiEndpoints= List.of(
  //         "/auth/register",
  //         "/auth/login"
  // );

  // public Predicate<ServerHttpRequest> isSecured =
  //         request -> openApiEndpoints
  //                 .stream()
  //                 .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
