import { GetServerSideProps } from "next";
import Gateways, { GatewaysProps } from "../../src/feature/gateway/Gateways";
import { GatewayDto } from "../../src/type/gateway-types";
import { getAllGateways } from "../../src/api/gateway-api";

const GatewaysPage: React.FC<GatewaysProps> = (props) => {
  return <Gateways gateways={props.gateways} />;
};

export const getServerSideProps: GetServerSideProps<{
  gateways: GatewayDto[];
}> = async () => {
  const data = await getAllGateways();
  return { props: { gateways: data } };
};

export default GatewaysPage;
