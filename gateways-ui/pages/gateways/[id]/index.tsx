import { GetServerSideProps } from "next";
import GatewayDetails, {
  GatewayDetailsProps,
} from "../../../src/feature/gateway/GatewayDetails";
import { findGateway } from "../../../src/api/gateway-api";
import { GatewayDto } from "../../../src/type/gateway-types";

const GatewayDetailsPage: React.FC<GatewayDetailsProps> = (props) => {
  return <GatewayDetails gateway={props.gateway} />;
};

export const getServerSideProps: GetServerSideProps<{
  gateway: GatewayDto;
}> = async (context) => {
  const id = context.params.id as string;
  const data = await findGateway(parseInt(id));
  return { props: { gateway: data } };
};

export default GatewayDetailsPage;
