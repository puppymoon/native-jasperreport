FROM ubuntu:22.04 as builder
RUN apt-get update && apt-get install -y libfreetype6-dev && apt-get install -y fontconfig

# use distroless as base image
#FROM gcr.io/distroless/base
FROM gcr.io/distroless/java11-debian11
COPY --from=builder /lib/x86_64-linux-gnu/libc.so.6 /lib/x86_64-linux-gnu/libc.so.6
COPY --from=builder /lib/x86_64-linux-gnu/libfreetype.so.6.18.1 /lib/x86_64-linux-gnu/libfreetype.so.6
COPY --from=builder /lib/x86_64-linux-gnu/libstdc++.so.6.0.30 /lib/x86_64-linux-gnu/libstdc++.so.6
COPY --from=builder /lib/x86_64-linux-gnu/libz.so.1.2.11 /lib/x86_64-linux-gnu/libz.so.1
COPY --from=builder /lib/x86_64-linux-gnu/libgcc_s.so.1 /lib/x86_64-linux-gnu/libgcc_s.so.1
COPY --from=builder /lib/x86_64-linux-gnu/libpng16.so.16.37.0 /lib/x86_64-linux-gnu/libpng16.so.16
COPY --from=builder /lib/x86_64-linux-gnu/libbrotlidec.so.1.0.9 /lib/x86_64-linux-gnu/libbrotlidec.so.1
COPY --from=builder /lib/x86_64-linux-gnu/libbrotlicommon.so.1.0.9 /lib/x86_64-linux-gnu/libbrotlicommon.so.1
COPY --from=builder /lib/x86_64-linux-gnu/libfontconfig.so.1.12.0 /lib/x86_64-linux-gnu/libfontconfig.so.1 
COPY --from=builder /lib64/ld-linux-x86-64.so.2 /lib64/ld-linux-x86-64.so.2

COPY target/native-jasperreport .
EXPOSE 8080
ENTRYPOINT ["/native-jasperreport"]
