/*
 * Copyright © 2023 Mark Raynsford <code@io7m.com> https://www.io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.canonmill.core;

import java.io.IOException;
import java.security.Provider;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * The {@code canonmill} keystore provider.
 */

public final class CMKeyStoreProvider extends Provider
{
  private static final String PROVIDER_NAME = "CANONMILL";
  private static final String KEYSTORE_TYPE = "CANONMILL";

  /**
   * The {@code canonmill} keystore provider.
   */

  public CMKeyStoreProvider()
  {
    super(
      providerName(),
      version(),
      "Provides directory based KeyStores"
    );
    this.put(
      "KeyStore." + providerName(),
      "com.io7m.canonmill.core.internal.CMKeyStore"
    );
  }

  /**
   * @return The {@code canonmill} provider name
   */

  public static String providerName()
  {
    return PROVIDER_NAME;
  }

  /**
   * @return The {@code canonmill} keystore type name
   */

  public static String keystoreType()
  {
    return KEYSTORE_TYPE;
  }

  private static String version()
  {
    try {
      try (var stream = CMKeyStoreProvider.class.getResourceAsStream(
        "/com/io7m/canonmill/core/internal/version.txt"
      )) {
        Objects.requireNonNull(stream, "stream");
        return new String(stream.readAllBytes(), UTF_8);
      }
    } catch (final IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
