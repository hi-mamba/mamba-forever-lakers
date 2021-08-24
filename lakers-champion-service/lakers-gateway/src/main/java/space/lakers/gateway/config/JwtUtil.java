package space.lakers.gateway.config;


/**
 * @author pankui
 * @date 2021/8/22
 * <pre>
 *
 * </pre>
 */
//@Component
public class JwtUtil {

  // @Value("${jwt.secret}")
  // private String secret;

  // private Key key;

  // @PostConstruct
  // public void init(){
  //     this.key = Keys.hmacShaKeyFor(secret.getBytes());
  // }

  // public Claims getAllClaimsFromToken(String token) {
  //     return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  // }

  // private boolean isTokenExpired(String token) {
  //     return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
  // }

  // public boolean isInvalid(String token) {
  //     return this.isTokenExpired(token);
  // }

}
