<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" encoding="UTF-8" />
  <xsl:strip-space elements="*" />
  <xsl:template match="/">
    <html>
      <head><title>Listado de Vinos</title></head>
      <body>
        <h1>Lista de Vinos Disponibles</h1>
        <ul>
          <xsl:for-each select="vinoteca/vino[disponible='true']">
            <li><xsl:value-of select="nombre" /> - <xsl:value-of select="@DO" /></li>
          </xsl:for-each>
        </ul>
        <h2>Tabla de Vinos</h2>
        <table border="1">
          <tr><th>Nombre</th><th>Tipo</th><th>Precio</th><th>DO</th><th>Disponible</th></tr>
          <xsl:for-each select="vinoteca/vino">
            <tr>
              <td><xsl:value-of select="nombre" /></td>
              <td><xsl:value-of select="tipo" /></td>
              <td><xsl:value-of select="precio" /> <xsl:value-of select="precio/@moneda" /></td>
              <td><xsl:value-of select="@DO" /></td>
              <td><xsl:value-of select="disponible" /></td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
