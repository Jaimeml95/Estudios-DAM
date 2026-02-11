<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="xml" encoding="UTF-8" indent="yes" />
  <xsl:strip-space elements="*" />
  <xsl:template match="/vinoteca">
    <catalogo>
      <xsl:for-each select="vino">
        <xsl:if test="disponible='true'">
          <producto>
            <xsl:attribute name="denominacion">
              <xsl:value-of select="@DO"/>
            </xsl:attribute>
            <xsl:attribute name="precio">
              <xsl:value-of select="precio"/>
            </xsl:attribute>
            <xsl:attribute name="moneda">
              <xsl:value-of select="precio/@moneda"/>
            </xsl:attribute>
            <nombre><xsl:value-of select="nombre"/></nombre>
            <region><xsl:value-of select="bodega/localizacion/region"/></region>
          </producto>
        </xsl:if>
      </xsl:for-each>
    </catalogo>
  </xsl:template>
</xsl:stylesheet>
