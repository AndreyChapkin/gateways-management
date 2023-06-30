module.exports = {
  async redirects() {
    return [
      {
        source: '/',
        destination: '/gateways',
        permanent: true,
      },
    ]
  },
  distDir: 'build',
}